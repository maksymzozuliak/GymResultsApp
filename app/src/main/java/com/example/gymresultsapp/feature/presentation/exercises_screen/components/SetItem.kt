package com.example.gymresultsapp.feature.presentation.exercises_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.gymresultsapp.feature.domain.model.Set
import com.example.gymresultsapp.ui.theme.darkGray
import com.example.gymresultsapp.ui.theme.gray

@Composable
fun SetItem(
    num: Int,
    set: Set,
    repColor: Color,
    colorMain: Color = MaterialTheme.colors.onPrimary,
    secondaryColor : Color = darkGray,
    textStyle: TextStyle = MaterialTheme.typography.body1
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            style = textStyle,
            color = secondaryColor,
            text = num.toString()
        )
        Spacer(modifier = Modifier
            .width(20.dp))
        Text(
            style = textStyle,
            color = colorMain,
            text = set.weight
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            style = textStyle.copy(fontSize = 16.sp),
            color = secondaryColor,
            text = "kg."
        )
        Spacer(modifier = Modifier
            .weight(1.0f))
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(repColor)
                .padding(horizontal = 10.dp),
            style = textStyle,
            color = colorMain,
            text = set.reps.toString()
        )
    }
}