package com.raywenderlich.android.datadrop.app

import android.app.Application

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raywenderlich.android.datadrop.model.DropDatabase
import com.raywenderlich.android.datadrop.model.MarkerColor
import com.raywenderlich.android.datadrop.model.MarkerColorDao


class DataDropApplication : Application() {

    companion object {
        private lateinit var instance: DataDropApplication

        lateinit var database: DropDatabase
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        database = Room.databaseBuilder(this, DropDatabase::class.java, "drop_database")
                .addCallback(roomDatabaseCallback)
                .build()
    }

    private val roomDatabaseCallback = object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            PopulateDbAsync(DataDropApplication.database).execute()
        }
    }

    private class PopulateDbAsync(db: DropDatabase) : AsyncTask<Void, Void, Void>() {
        private val markerColorDao: MarkerColorDao = db.markerDao()

        override fun doInBackground(vararg p0: Void?): Void? {
            var markerColor = MarkerColor(MarkerColor.RED_COLOR)
            markerColorDao.inser(markerColor)
            markerColor = MarkerColor(MarkerColor.BLUE_COLOR)
            markerColorDao.inser(markerColor)
            markerColor = MarkerColor(MarkerColor.GREEN_COLOR)
            markerColorDao.inser(markerColor)
            return null
        }
    }
}