package tsisyk.app.guess_game.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import tsisyk.app.guess_game.R
import tsisyk.app.guess_game.databinding.GameFragmentBinding

@Suppress("DEPRECATION")
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.eventGameFinish.observe(this, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
            }
        })

        viewModel.eventBuzz.observe(this, Observer { buzzType ->
            if (buzzType != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })
        return binding.root
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                buzzer.vibrate(pattern, -1)
            }
        }
    }

    fun mutableList(): MutableList<String> =
        mutableListOf("The Godfather", "The Shawshank Redemption", "Pulp Fiction", "Star Wars", "The Dark Knight", "GoodFellas", "The Matrix ", "Schindler\'s List", "Indiana Jones", "Fight Club", "Saving Private Ryan", "Back to the Future", "Gladiator", "The Lord of the Rings", "Braveheart", "Inception", "Jaws ", "Titanic", "Jurassic Park", "Terminator", "Rocky ", "Akira", "Underground", "The Big Sleep", "The Graduate", "The Hustler", "Anatomy of a Murder", "Before Sunset", "X-Men", "Papillon", "Beauty and the Beast", "The Night of the Hunter", "Roman Holiday", "Castle in the Sky", "Notorious", "Pirates of the Caribbean", "A Fistful of Dollars", "Yip Man", "The Imitation Game", "The King's Speech", "Dog Day Afternoon", "Barry Lyndon", "The Truman Show", "Throne of Blood", "Harry Potter", "Monsters, Inc", "Guardians of the Galaxy", "Memories of Murder\n", "Groundhog Day\n", "The Battle of Algiers", "Goodfellas", "12 Angry Men", "Léon: The Professional", "Once Upon a Time in the West", "The Pianist", "The Green Mile")

    /* fun mutableList() =
        mutableListOf(
            "The Godfather",
            "The Shawshank Redemption",
            "Pulp Fiction",
            "Star Wars",
            "The Dark Knight",
            "GoodFellas",
            "The Matrix ",
            "Schindler\'s List",
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
            "Rocky ",
            "Akira",
            "Underground",
            "The Big Sleep",
            "The Graduate",
            "The Hustler",
            "Anatomy of a Murder",
            "Before Sunset",
            "X-Men",
            "Papillon",
            "Beauty and the Beast",
            "The Night of the Hunter",
            "Roman Holiday",
            "Castle in the Sky",
            "Notorious",
            "Pirates of the Caribbean",
            "A Fistful of Dollars",
            "Yip Man",
            "The Imitation Game",
            "The King's Speech",
            "Dog Day Afternoon",
            "Barry Lyndon",
            "The Truman Show",
            "Throne of Blood",
            "Harry Potter",
            "Monsters, Inc",
            "Guardians of the Galaxy",
            "Memories of Murder\n",
            "Groundhog Day\n",
            "The Battle of Algiers",
            "Goodfellas",
            "12 Angry Men",
            "Léon: The Professional",
            "Once Upon a Time in the West",
            "The Pianist",
            "The Green Mile"
        )
*/
}