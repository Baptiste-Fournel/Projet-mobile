package com.worldline.quiz

import com.worldline.quiz.data.dataclass.Quiz
import io.github.xxfast.kstore.KStore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun getKStore(): KStore<Quiz>?