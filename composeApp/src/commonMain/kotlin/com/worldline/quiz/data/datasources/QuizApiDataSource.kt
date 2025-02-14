import com.worldline.quiz.data.dataclass.Question
import com.worldline.quiz.data.datasources.QuizDataSource

class QuizApiDatasource {
    fun getAllQuestions(): List<Question> {
        return QuizDataSource.questions
    }
}

