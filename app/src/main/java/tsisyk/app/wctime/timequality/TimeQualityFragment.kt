package tsisyk.app.wctime.timequality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tsisyk.app.wctime.R
import tsisyk.app.wctime.databinding.FragmentTimeQualityBinding

class TimeQualityFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentTimeQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_time_quality, container, false)

        val application = requireNotNull(this.activity).application

        return binding.root
    }
}
