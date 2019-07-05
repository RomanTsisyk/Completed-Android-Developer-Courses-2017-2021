package tsisyk.app.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import tsisyk.app.quizapp.databinding.FragmentGameBinding
import kotlin.math.min

class QuizFragment : Fragment() {
    data class Quiz(
            val text: String,
            val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Quiz> = mutableListOf(
            Quiz(text = "What country is FIFA headquartered in?",
                    answers = listOf("Switzerland", "Serbia", "Slovenia", "Slovakia", "Switzerland")),
            Quiz(text = "What nation has played in every world cup?",
                    answers = listOf("Brazil", "Poland", "Panama", "Portugal", "Brazil")),
            Quiz(text = "Which of these countries did not participate in the first World Cup?",
                    answers = listOf("Germany", "Romania", "Peru", "Germany", "USA")),
            Quiz(text = "How many teams participated in the first World Cup?",
                    answers = listOf("13", "16", "17", "13", "12")),
            Quiz(text = ". How many different countries have won a World Cup?",
                    answers = listOf("8", "10", "11", "8", "9")),
            Quiz(text = "Who makes the official soccer ball of the 2014 World Cup?",
                    answers = listOf("Adidas", "Puma", "Adidas", "Nice", "Reebok")),
            Quiz(text = "Which African country has competed in 7 World Cups, the most of any African country?",
                    answers = listOf("Cameroon", "Nigeria", "Cameroon", "Morocco", "Ghana")),
            Quiz(text = "Ernie Brandts is the only player to score a goal for both teams during a World Cup match.\n" +
                    "What country did Ernie play for?",
                    answers = listOf("The Netherlands", "The Netherlands", "Germany", "Portugal", "Brazil"))
    )

    lateinit var currentQuestion: Quiz
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.quiz = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                    R.id.fourthAnswerRadioButton -> answerIndex = 4
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate( R.id.action_quizFragment_to_quizWonFragment)

                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                }
            }
        }
        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.title_android_soccer_question,
                        questionIndex + 1, numQuestions)
    }
}
