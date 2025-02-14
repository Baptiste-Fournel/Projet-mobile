package com.worldline.quiz.data.datasources

import com.worldline.quiz.data.dataclass.Answer
import com.worldline.quiz.data.dataclass.Question

class MockDataSource {

     fun generateDummyQuestionsList():List<Question>{
        return listOf(
            Question(
                1,
                "Android is a great platform ?",
                1,
                listOf(
                    Answer( 1,"YES"),
                    Answer(2,"NO")
                )
            ),
            Question(
                1,
                "Android is a bad platform ?",
                2,
                listOf(
                    Answer( 1,"YES"),
                    Answer(2,"NO")
                )
            )
        )

    }

}