package tsisyk.app.babysleeptracker.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import tsisyk.app.babysleeptracker.R
import tsisyk.app.babysleeptracker.convertDurationToFormatted
import tsisyk.app.babysleeptracker.convertNumericQualityToString
import tsisyk.app.babysleeptracker.database.SleepNight


@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight?) {
    item?.let {
        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight?) {
    item?.let {
        text = convertNumericQualityToString(item.sleepQuality, context.resources)
    }
}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight) {
    setImageResource(
        when (item.sleepQuality) {
            0 -> R.drawable.baby
            1 -> R.drawable.cry_baby
            2 -> R.drawable.diaper
            3 -> R.drawable.feeding_bottle
            else -> R.drawable.sand_clock
        }
    )
}