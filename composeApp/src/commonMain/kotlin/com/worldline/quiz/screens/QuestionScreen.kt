package com.worldline.quiz.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.worldline.quiz.data.datasources.QuizDataSource
import kotlinx.coroutines.delay

@Composable
fun questionScreen(onFinish: (Int, Int) -> Unit) {
    val questions = QuizDataSource.questions
    var questionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var hintVisible by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    var lives by remember { mutableStateOf(5) }
    var canRetry by remember { mutableStateOf(false) }
    var quizFinished by remember { mutableStateOf(false) }

    LaunchedEffect(questionIndex) {
        if (quizFinished) return@LaunchedEffect

        timeLeft = 10
        hintVisible = false
        selectedAnswer = -1
        canRetry = false

        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
            if (timeLeft == 5) hintVisible = true
        }

        if (timeLeft == 0) {
            if (questionIndex == questions.size - 1) {
                quizFinished = true
                onFinish(score, questions.size)
            } else {
                if (canRetry) {
                    lives--
                    if (lives <= 0) {
                        lives = 0
                    }
                } else {
                    goToNextQuestion(questions, questionIndex, score, lives, { questionIndex++ }, {
                        quizFinished = true
                        onFinish(score, questions.size)
                    })
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < lives) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp).padding(end = 4.dp)
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            elevation = 6.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Question ${questionIndex + 1} / ${questions.size}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = questions[questionIndex].label,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                if (hintVisible) {
                    Text(
                        text = "💡 Indice : ${questions[questionIndex].hint}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                Text(
                    text = "⏳ Temps restant : $timeLeft sec",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        timeLeft > 6 -> Color.Green
                        timeLeft > 3 -> Color(0xFFFFA726)
                        else -> Color.Red
                    },
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            questions[questionIndex].answers.forEach { answer ->
                val isSelected = selectedAnswer == answer.id
                val backgroundColor by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.2f) else Color.Transparent,
                    animationSpec = tween(durationMillis = 300)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(6.dp))
                        .clickable { selectedAnswer = answer.id }
                        .padding(12.dp)
                ) {
                    Text(
                        text = answer.label,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Button(
            modifier = Modifier.padding(top = 20.dp).scale(if (selectedAnswer != -1) 1.02f else 1f),
            onClick = {
                if (selectedAnswer == questions[questionIndex].correctAnswerId) {
                    score++
                    goToNextQuestion(questions, questionIndex, score, lives, { questionIndex++ }, {
                        quizFinished = true
                        onFinish(score, questions.size)
                    })
                } else {
                    if (lives > 1) {
                        lives--
                        canRetry = true
                    } else {
                        lives = 0
                        goToNextQuestion(questions, questionIndex, score, lives, { questionIndex++ }, {
                            quizFinished = true
                            onFinish(score, questions.size)
                        })
                    }
                }
            },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            elevation = ButtonDefaults.elevation(4.dp),
            enabled = selectedAnswer != -1
        ) {
            Icon(
                imageVector = if (questionIndex < questions.size - 1) Icons.AutoMirrored.Filled.ArrowForward else Icons.Filled.Done,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = if (questionIndex < questions.size - 1) "Suivant" else "Terminé",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            progress = (questionIndex + 1) / questions.size.toFloat(),
            color = MaterialTheme.colors.primary
        )
    }
}

fun goToNextQuestion(
    questions: List<com.worldline.quiz.data.dataclass.Question>,
    questionIndex: Int,
    score: Int,
    lives: Int,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    if (questionIndex < questions.size - 1) {
        onNext()
    } else {
        onFinish()
    }
}