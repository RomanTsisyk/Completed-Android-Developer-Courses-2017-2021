package tsisyk.app.guess_game.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tsisyk.app.guess_game.R
import tsisyk.app.guess_game.databinding.TitleFragmentBinding

/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.title_fragment, container, false)

        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
        }
        return binding.root
    }
}
