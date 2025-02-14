import com.worldline.quiz.data.dataclass.Question
import com.worldline.quiz.data.dataclass.Quiz
import com.worldline.quiz.getKStore
import io.github.xxfast.kstore.KStore

class QuizKStoreDataSource {
    private val kStoreQuiz: KStore<Quiz>? = getKStore()

    suspend fun getUpdateTimeStamp(): Long = kStoreQuiz?.get()?.updateTime ?: 0L

    suspend fun setUpdateTimeStamp(timeStamp: Long) {
        kStoreQuiz?.update { quiz: Quiz? ->
            quiz?.copy(updateTime = timeStamp)
        }
    }

    suspend fun getAllQuestions(): List<Question> {
        return kStoreQuiz?.get()?.questions ?: emptyList()
    }

    suspend fun insertQuestions(newQuestions: List<Question>) {
        kStoreQuiz?.update { quiz: Quiz? ->
            quiz?.copy(questions = newQuestions)
        }
    }

    suspend fun resetQuizKStore() {
        kStoreQuiz?.delete()
        kStoreQuiz?.set(Quiz(emptyList(), 0L))
    }
}