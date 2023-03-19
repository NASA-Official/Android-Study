package com.nassafy.aro.hilt.compseviewpractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CountryPlaceChips() {
    LazyRow(
        Modifier
            .background(Color.Gray)
            .fillMaxWidth(1f)) {
        //TODO change items
        items(30) {
            Box(
                Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center) {
                Text(text = "$it", color = Color.White)
            }
        }
    }
}