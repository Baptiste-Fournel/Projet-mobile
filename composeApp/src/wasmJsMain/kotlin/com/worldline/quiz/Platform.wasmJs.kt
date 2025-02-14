package com.worldline.quiz

import com.worldline.quiz.data.dataclass.Quiz
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual fun getKStore(): KStore<Quiz>? {
        return storeOf(key = "kstore_quiz")
     }
