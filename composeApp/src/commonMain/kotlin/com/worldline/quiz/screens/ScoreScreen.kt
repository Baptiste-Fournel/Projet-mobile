package com.worldline.quiz.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun scoreScreen(score: Int, total: Int, onResetButtonPushed: () -> Unit) {
    val scorePercentage = score.toFloat() / total.toFloat()
    val backgroundColor = when {
        scorePercentage > 0.8 -> Color(0xFF4CAF50) // Vert
        scorePercentage > 0.5 -> Color(0xFFFFA726) // Orange
        else -> Color(0xFFD32F2F) // Rouge
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            backgroundColor = backgroundColor
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Résultat du Quiz",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$score / $total",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress = scorePercentage,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onResetButtonPushed() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text("Rejouer", color = backgroundColor)
                }
            }
        }
    }
}
