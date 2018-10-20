package com.raywenderlich.android.datadrop.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DropDbHelper(context: Context)
    : SQLiteOpenHelper(context, DropDbSchema.DB_NAME, null, DropDbSchema.VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table " + DropDbSchema.DropTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                DropDbSchema.DropTable.Colums.ID + " text, " +
                DropDbSchema.DropTable.Colums.LATITUDE + " real, " +
                DropDbSchema.DropTable.Colums.LONGITUDE + " real, " +
                DropDbSchema.DropTable.Colums.DROP_MESSAGE + " text," +
                DropDbSchema.DropTable.Colums.MARKER_COLOR + " integer" + ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("alter table " +
                    DropDbSchema.DropTable.NAME + " add column " +
                    DropDbSchema.DropTable.Colums.MARKER_COLOR + " integer;")
        }
    }
}