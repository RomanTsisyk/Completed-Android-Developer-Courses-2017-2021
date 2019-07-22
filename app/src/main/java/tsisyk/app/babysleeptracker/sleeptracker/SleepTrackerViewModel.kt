package tsisyk.app.babysleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tsisyk.app.babysleeptracker.database.SleepDatabaseDao

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {
}

