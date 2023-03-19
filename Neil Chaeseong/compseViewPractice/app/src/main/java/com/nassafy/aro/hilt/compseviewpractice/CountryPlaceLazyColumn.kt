package com.nassafy.aro.ui.view.custom

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun CountryPlaceLazyColumn() {
    LazyColumn (Modifier.fillMaxWidth(1f)){
        // TODO Change Array
        items(30) {
            CountryPlaceLazyColumnItem(order = it)
        }
    }
}