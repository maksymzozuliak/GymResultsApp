package com.example.gymresultsapp.feature.presentation.exercises_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.gymresultsapp.feature.domain.model.Exercise

@Composable
fun ExerciseItem(
    exercise: Exercise,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    cutCornerSize: Dp = 30.dp
) {
    Box(
        modifier = modifier,
    ) {
        Canvas(modifier = Modifier.matchParentSize()){
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = exercise.group.color,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(exercise.group.color.toArgb(), 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(
                        cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f
                    ),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.h1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(
                        Color(
                            ColorUtils.blendARGB(exercise.group.color.toArgb(), 0x000000, 0.2f)
                        ),
                    )
                    .padding(vertical = 8.dp)
            ) {
                for (i in 0 until exercise.sets.size) {
                    SetItem(
                        num = i+1,
                        set = exercise.sets[i],
                        repColor = Color(
                            ColorUtils.blendARGB(exercise.group.color.toArgb(), 0x000000, 0.26f)
                        )
                    )
                    if (i != exercise.sets.size-1) {
                        Divider(
                            color = Color(
                                ColorUtils.blendARGB(exercise.group.color.toArgb(), 0x000000, 0.4f)
                            )
                            ,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
            }

        }
    }
}