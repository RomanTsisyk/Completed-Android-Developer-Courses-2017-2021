package tsisyk.app.wctime.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_wc")
data class TImeWC(

    @PrimaryKey(autoGenerate = true)
    var timeId: Long = 0L,

    @ColumnInfo(name = "start_time")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "quality_ratting")
    var timeQuality: Int = -0
)

