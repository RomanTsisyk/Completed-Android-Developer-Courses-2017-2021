package com.raywenderlich.android.datadrop.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.ContentValues
import android.util.Log
import com.raywenderlich.android.datadrop.app.DataDropApplication
import com.raywenderlich.android.datadrop.viewmodel.ClearAllDropsListener
import com.raywenderlich.android.datadrop.viewmodel.ClearDropsListener
import com.raywenderlich.android.datadrop.viewmodel.DropsInsertListener
import java.io.IOException

class SQLiteRepository : DropRepository {

    private val database = DropDbHelper(DataDropApplication.getAppContext()).writableDatabase

    private fun getDropContentValue(drop: Drop): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(DropDbSchema.DropTable.Colums.ID, drop.id)
        contentValues.put(DropDbSchema.DropTable.Colums.LATITUDE, drop.latLng.latitude)
        contentValues.put(DropDbSchema.DropTable.Colums.LONGITUDE, drop.latLng.longitude)
        contentValues.put(DropDbSchema.DropTable.Colums.DROP_MESSAGE, drop.dropMessage)
        contentValues.put(DropDbSchema.DropTable.Colums.MARKER_COLOR, drop.markerColor)
        return contentValues
    }

    private fun queryDrops(where: String?, whereArgs: Array<String>?): DropCursorWrapper {
        val cursor = database.query(DropDbSchema.DropTable.NAME, null, where, whereArgs, null, null, null)
        return DropCursorWrapper(cursor)
    }

    override fun addDrop(drop: Drop, listener: DropsInsertListener) {
        val contentValues = getDropContentValue(drop)
        val result = database.insert(DropDbSchema.DropTable.NAME, null, contentValues)
        if (result != -1L) {
            listener.dropsInserted(drop)
        }
    }

    override fun getDrops(): LiveData<List<Drop>> {

        val liveData = MutableLiveData<List<Drop>>()
        val drops = mutableListOf<Drop>()
        val cursor = queryDrops(null, null)

        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                drops.add(cursor.getDrop())
                cursor.moveToNext()
            }
        } catch (e: IOException) {
            Log.e("SQLiteRepository", "Error drops")
        } finally {
            cursor.close()
        }

        liveData.value = drops
        return liveData
    }

    override fun clearDrop(drop: Drop, listener: ClearDropsListener) {
        val result = database.delete(
                DropDbSchema.DropTable.NAME,
                DropDbSchema.DropTable.Colums.ID + " = ?",
                arrayOf(drop.id))

        if (result != 0) {
            listener.dropsCleared(drop)
        }
    }

    override fun clearAllDrops(listener: ClearAllDropsListener) {
        val result = database.delete(DropDbSchema.DropTable.NAME, null, null)
        if (result != 0) {
            listener.allDropsCleared()
        }
    }

}