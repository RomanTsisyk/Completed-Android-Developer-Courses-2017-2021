package tsisyk.app.wctime.timetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tsisyk.app.wctime.R
import tsisyk.app.wctime.databinding.FragmentTimeTrackerBinding


class TimeTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentTimeTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_time_tracker, container, false)
        return binding.root
    }
}
