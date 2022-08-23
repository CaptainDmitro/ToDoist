package ru.captaindmitro.todoist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.captaindmitro.todoist.data.LocalDataSource
import ru.captaindmitro.todoist.data.RepositoryImpl
import ru.captaindmitro.todoist.data.database.TodoDao
import ru.captaindmitro.todoist.domain.Repository
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(todoDao: TodoDao, @IoDispatcher dispatcher: CoroutineDispatcher): LocalDataSource = LocalDataSource.Base(todoDao, dispatcher)

    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource, @IoDispatcher dispatcher: CoroutineDispatcher): Repository = RepositoryImpl(localDataSource, dispatcher)

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher