package com.worldline.quiz.data.datasources

import com.worldline.quiz.data.dataclass.Question
import com.worldline.quiz.data.dataclass.Answer

object QuizDataSource {
    val questions: List<Question> = listOf(
        Question(
            id = 1,
            label = "Quel est le langage principal utilisé pour développer des applications Android ?",
            correctAnswerId = 2,
            answers = listOf(
                Answer(1, "Swift"),
                Answer(2, "Kotlin"),
                Answer(3, "C#"),
                Answer(4, "Ruby")
            ),
            hint = "C'est un langage adopté par Google en 2017."
        ),
        Question(
            id = 2,
            label = "Quel fichier décrit les autorisations et configurations d'une application Android ?",
            correctAnswerId = 1,
            answers = listOf(
                Answer(1, "AndroidManifest.xml"),
                Answer(2, "Config.xml"),
                Answer(3, "Build.gradle"),
                Answer(4, "Settings.gradle")
            ),
            hint = "Il contient la déclaration des activités et des services."
        ),
        Question(
            id = 3,
            label = "Quelle bibliothèque est utilisée pour gérer les requêtes HTTP en Kotlin Multiplatform ?",
            correctAnswerId = 3,
            answers = listOf(
                Answer(1, "Retrofit"),
                Answer(2, "Volley"),
                Answer(3, "Ktor"),
                Answer(4, "OkHttp")
            ),
            hint = "Développée par JetBrains pour le multiplateforme."
        ),
        Question(
            id = 4,
            label = "Quel concept permet de gérer les états en Jetpack Compose ?",
            correctAnswerId = 2,
            answers = listOf(
                Answer(1, "LiveData"),
                Answer(2, "State"),
                Answer(3, "Coroutines"),
                Answer(4, "ViewModel")
            ),
            hint = "C'est un élément fondamental de la composition déclarative."
        ),
        Question(
            id = 5,
            label = "Quelle annotation est utilisée pour sérialiser des classes en Kotlin ?",
            correctAnswerId = 4,
            answers = listOf(
                Answer(1, "@JsonSerializable"),
                Answer(2, "@Parcelize"),
                Answer(3, "@Serializable"),
                Answer(4, "@Json")
            ),
            hint = "Elle fait partie de kotlinx.serialization."
        ),
        Question(
            id = 6,
            label = "Quel framework permet de tester des applications Android de manière automatisée ?",
            correctAnswerId = 1,
            answers = listOf(
                Answer(1, "Espresso"),
                Answer(2, "JUnit"),
                Answer(3, "Mockito"),
                Answer(4, "Selenium")
            ),
            hint = "Il est spécifiquement conçu pour tester les UI Android."
        )
    )
}
