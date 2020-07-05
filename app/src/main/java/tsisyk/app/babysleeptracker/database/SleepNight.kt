package tsisyk.app.babysleeptracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight  (
        @PrimaryKey (autoGenerate = true)
        var nightId: Long = 0L,

        @ColumnInfo(name = "start_time")
        val startTimeMilli: Long =  System.currentTimeMillis(),

        @ColumnInfo(name = "end_time")
        var endTimeMilli: Long = startTimeMilli,

        @ColumnInfo(name = "quality_rating")
        var sleepQuality: Int = -1
)
