package com.worldline.quiz.data

import QuizKStoreDataSource
import com.worldline.quiz.data.dataclass.Question
import com.worldline.quiz.data.datasources.MockDataSource
import com.worldline.quiz.data.datasources.QuizDataSource
import kotlinx.datetime.Clock

class QuizRepository {

    private val mockDataSource = MockDataSource()
    private val quizKStoreDataSource = QuizKStoreDataSource()

    /**
     * Récupère les questions depuis QuizDataSource (en dur)
     */
    private suspend fun fetchQuiz(): List<Question> = QuizDataSource.questions

    /**
     * Efface les anciennes questions stockées et insère les nouvelles questions en mémoire
     */
    private suspend fun fetchAndStoreQuiz(): List<Question> {
        quizKStoreDataSource.resetQuizKStore()
        val questions = fetchQuiz()
        quizKStoreDataSource.insertQuestions(questions)
        quizKStoreDataSource.setUpdateTimeStamp(Clock.System.now().epochSeconds)
        return questions
    }

    /**
     * Vérifie si les questions doivent être mises à jour.
     * Si le cache est trop ancien ou inexistant, on recharge les questions.
     */
    suspend fun updateQuiz(): List<Question> {
        return try {
            val lastRequest = quizKStoreDataSource.getUpdateTimeStamp()
            if (lastRequest == 0L || Clock.System.now().epochSeconds - lastRequest > 300000) {
                fetchAndStoreQuiz()
            } else {
                quizKStoreDataSource.getAllQuestions()
            }
        } catch (e: NullPointerException) {
            fetchAndStoreQuiz()
        } catch (e: Exception) {
            e.printStackTrace()
            mockDataSource.generateDummyQuestionsList()
        }
    }
}
