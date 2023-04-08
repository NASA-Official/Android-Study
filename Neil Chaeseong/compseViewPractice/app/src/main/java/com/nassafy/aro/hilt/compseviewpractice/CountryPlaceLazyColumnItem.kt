package com.nassafy.aro.ui.view.custom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CountryPlaceLazyColumnItem(order: Int) {
    Card(
        Modifier
            .padding(12.dp)
            .border(width = 4.dp, color = Color.Black)
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("Kotlin World ${order}")
        }
    }
}