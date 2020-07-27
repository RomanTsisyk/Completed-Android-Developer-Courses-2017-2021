package tsisyk.app.babysleeptracker.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tsisyk.app.babysleeptracker.database.SleepNight
import tsisyk.app.babysleeptracker.databinding.ListItemSleepNightBinding


class SleepNightAdapter(val clickListener: SleepNightListener) : ListAdapter<SleepNight,
        SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root) {

/*         fun bind(item: SleepNight) {
          var res = itemView.context.resources
           binding.sleepLength.text =
               convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
           binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
           binding.qualityImage.setImageResource(
               when (item.sleepQuality) {
                   0 -> R.drawable.baby
                   1 -> R.drawable.cry_baby
                   2 -> R.drawable.diaper
                   3 -> R.drawable.feeding_bottle
                   else -> R.drawable.sand_clock
               }
           )*/

        fun bind(clickListener: SleepNightListener, item: SleepNight) {
            binding.sleep = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
       }

       companion object {
           fun from(parent: ViewGroup): ViewHolder {
               val layoutInflater = LayoutInflater.from(parent.context)
               val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
               return ViewHolder(binding)
           }
       }
   }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
   override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
       return oldItem.nightId == newItem.nightId
   }

   override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
       return oldItem == newItem
   }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
   fun onClick(night: SleepNight) = clickListener(night.nightId)
}