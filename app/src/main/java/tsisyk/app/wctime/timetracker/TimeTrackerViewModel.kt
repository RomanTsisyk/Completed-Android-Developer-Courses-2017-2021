package tsisyk.app.wctime.timetracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tsisyk.app.wctime.database.TimeDatabaseDao


class TimeTrackerViewModel(
        val database: TimeDatabaseDao,
        application: Application) : AndroidViewModel(application) {
}

