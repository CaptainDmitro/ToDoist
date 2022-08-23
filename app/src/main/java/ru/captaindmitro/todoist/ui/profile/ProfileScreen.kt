package ru.captaindmitro.todoist.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.captaindmitro.todoist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.clip(CircleShape)
        )
        Card(
            modifier = Modifier.fillMaxWidth().height(64.dp)
        ) {
            Text(text = "Upgrade to premium")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Card(
                modifier = Modifier
                    .height(72.dp)
                    .weight(.5f)
            ) {
                Text("Tast A")
            }
            Card(
                modifier = Modifier
                    .height(72.dp)
                    .weight(.5f)
            ) {
                Text("Tast B")
            }
        }
    }

}