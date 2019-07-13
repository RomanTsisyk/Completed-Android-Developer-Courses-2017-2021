package tsisyk.app.guess_game.screens.game

import androidx.lifecycle.ViewModel
import timber.log.Timber
import kotlin.collections.mutableListOf as mutableListOf1

class GameViewModel : ViewModel() {

    // The current word
    var word = ""

    // The current score
    var score = 0

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.plant(Timber.DebugTree())
    }

    private fun resetList() {
        wordList = mutableListOf1(


            "The Godfather",
            "The Shawshank Redemption<",
            "Pulp Fiction",
            "Star Wars",
            "The Dark Knight",
            "GoodFellas",
            "The Matrix ",
            " Schindler\'s List",
            "Indiana Jones",
            "Fight Club",
            "Saving Private Ryan",
            "Back to the Future",
            "Gladiator",
            "The Lord of the Rings",
            "Braveheart",
            "Inception",
            "Jaws ",
            "Titanic",
            "Jurassic Park",
            "Terminator",
            "Rocky "
            /*    getString(R.string.item1),
                getString(R.string.item2),
                getString(R.string.item3),
                getString(R.string.item4),
                getString(R.string.item5),
                getString(R.string.item6),
                getString(R.string.item7),
                getString(R.string.item8),
                getString(R.string.item9),
                getString(R.string.item10),
                getString(R.string.item11),
                getString(R.string.item12),
                getString(R.string.item13),
                getString(R.string.item14),
                getString(R.string.item15),
                getString(R.string.item16),
                getString(R.string.item17),
                getString(R.string.item18),
                getString(R.string.item19),
                getString(R.string.item20),
                getString(R.string.item21)*/
        )
        wordList.shuffle()
    }


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //       gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
    }


    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }

}