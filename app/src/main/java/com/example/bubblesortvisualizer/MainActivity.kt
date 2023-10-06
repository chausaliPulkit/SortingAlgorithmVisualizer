package com.example.bubblesortvisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bubblesortvisualizer.presentation.SortViewModel
import com.example.bubblesortvisualizer.ui.theme.BubbleSortVisualizerTheme
import com.example.bubblesortvisualizer.ui.theme.offPink
import com.example.bubblesortvisualizer.ui.theme.offWhite

class MainActivity : ComponentActivity() {
    private val sortViewModel = SortViewModel()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleSortVisualizerTheme {
                window.statusBarColor = offPink.toArgb()
                window.navigationBarColor = offPink.toArgb()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(offWhite)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Button(
                        onClick = {
                            sortViewModel.startSorting()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = offPink)
                    ) {
                        Text(
                            text = "Start Sorting",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                    }
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            sortViewModel.listToSort,
                            key = {
                                it.id
                            }
                        ) {
                            val borderStroke = if (it.isCurrentlyCompared) {
                                BorderStroke(
                                    width = 4.dp, brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xff74f7f3),
                                            Color(0xff8af293)
                                        ), // Define your gradient colors here
                                        start = Offset(0f, 0f),
                                        end = Offset(360f, 360f),
                                        tileMode = TileMode.Clamp
                                    )
                                )
                            } else {
                                BorderStroke(width = 0.dp, Color.Transparent)
                            }

                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(it.color, RoundedCornerShape(16.dp))
                                    .border(borderStroke, RoundedCornerShape(16.dp))
                                    .animateItemPlacement(
                                        tween(300)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${it.value}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }

                }

            }
        }
    }
}
