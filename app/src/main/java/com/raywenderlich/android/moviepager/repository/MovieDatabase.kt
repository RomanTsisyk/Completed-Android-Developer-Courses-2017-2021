/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.moviepager.repository

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.raywenderlich.android.moviepager.bgThread
import java.io.IOException
import java.nio.charset.StandardCharsets

@Database(entities = [(Movie::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao

  companion object {
    private const val TAG = "MovieDatabase"
    private var instance: MovieDatabase? = null

    @Synchronized
    fun get(context: Context): MovieDatabase {
      if (instance == null) {
        instance = Room.databaseBuilder(context.applicationContext,
            MovieDatabase::class.java, "MovieDatabase")
            .addCallback(object : RoomDatabase.Callback() {
              override fun onCreate(db: SupportSQLiteDatabase) {
                  fillInDatabase(context.applicationContext)
              }
            }).build()
      }
      return instance!!
    }

    private fun fillInDatabase(context: Context) {
        bgThread {
            val jsonString = readJson(context)

            if (jsonString != null) {
                val movieData = Gson().fromJson(jsonString, MovieData::class.java)
                get(context).movieDao().insert(movieData.movies)
                Log.v(TAG, movieData.toString())
            }
        }
    }

    private fun readJson(context: Context): String? {
      var json: String? = null

      try {
        val inputStream = context.assets.open("movie_data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, StandardCharsets.UTF_8)
      } catch (e: IOException) {
        Log.e(TAG, "Error reading JSON data ::: " + e.message, e)
      }

      return json
    }
  }
}