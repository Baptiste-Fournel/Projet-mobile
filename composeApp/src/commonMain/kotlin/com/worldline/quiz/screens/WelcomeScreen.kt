package com.worldline.quiz.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun welcomeScreen(onStartButtonPushed: () -> Unit) {
    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        showContent = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976D2), Color(0xFF64B5F6))
                )
            )
            .padding(20.dp)
    ) {
        AnimatedVisibility(visible = showContent, enter = fadeIn()) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Bienvenue au Quiz",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Testez vos connaissances en Kotlin Multiplatform et Compose.",
                        fontSize = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { onStartButtonPushed() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Démarrer le Quiz", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
