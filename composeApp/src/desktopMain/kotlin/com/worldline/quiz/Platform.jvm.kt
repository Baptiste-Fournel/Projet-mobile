package com.worldline.quiz

import com.worldline.quiz.data.dataclass.Quiz
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun getKStore(): KStore<Quiz>? {
        return storeOf("quiz.json".toPath())
}