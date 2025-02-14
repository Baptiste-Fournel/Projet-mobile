package com.worldline.quiz.data.dataclass

@kotlinx.serialization.Serializable
data class Quiz(var questions: List<Question>,  val updateTime:Long=0L)