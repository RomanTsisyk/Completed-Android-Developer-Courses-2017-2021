package tsisyk.app.babysleeptracker.sleepdetail

import tsisyk.app.babysleeptracker.database.SleepDatabaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SleepDetailViewModelFactory(
    private val sleepNightKey: Long,
    private val dataSource: SleepDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)) {
            return SleepDetailViewModel(sleepNightKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
