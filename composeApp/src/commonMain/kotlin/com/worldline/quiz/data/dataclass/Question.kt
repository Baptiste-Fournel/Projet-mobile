package com.worldline.quiz.data.dataclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val label: String,
    @SerialName("correct_answer_id") val correctAnswerId: Int,
    val answers: List<Answer>,
    val hint: String = ""
)