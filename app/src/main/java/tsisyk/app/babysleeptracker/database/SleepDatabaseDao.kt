package tsisyk.app.babysleeptracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import tsisyk.app.babysleeptracker.database.SleepNight as SleepNight1
@Dao
interface SleepDatabaseDao{


    @Insert
    fun insert(night: SleepNight1)

    @Update
    fun update(night: SleepNight1)

    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight1?

    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight1>>

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight1?
}


