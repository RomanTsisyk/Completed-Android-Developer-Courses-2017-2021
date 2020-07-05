package com.raywenderlich.android.datadrop.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DropDao {

    @Insert
    fun insert(drop: Drop)

    @Delete
    fun clear(vararg drop: Drop)

    @Query("SELECT * FROM drop_table ORDER by dropMessage ASC")
    fun getAllDrops(): LiveData<List<Drop>>

}