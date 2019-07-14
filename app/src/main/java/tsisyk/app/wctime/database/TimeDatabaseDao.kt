
package tsisyk.app.wctime.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TimeDatabaseDao{

    @Insert
    fun insert (time: TImeWC)

    @Update
    fun update  (time: TImeWC)

    @Query("SELECT * from time_wc WHERE timeId =:key")
    fun get(key: Long): TImeWC

    @Query("DELETE FROM time_wc")
    fun clear()

    @Query("SELECT * from time_wc ORDER BY timeId DESC")
    fun deleteAll(): LiveData<List<TImeWC>>

    @Query("SELECT * from time_wc ORDER BY timeId DESC LIMIT 1")
    fun getLasrTime(): TImeWC?

}
