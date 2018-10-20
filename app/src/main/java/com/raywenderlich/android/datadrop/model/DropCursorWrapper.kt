package com.raywenderlich.android.datadrop.model

import android.database.Cursor
import android.database.CursorWrapper
import com.google.android.gms.maps.model.LatLng

class DropCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {
    fun getDrop(): Drop {
        val id = getString(getColumnIndex(DropDbSchema.DropTable.Colums.ID))
        val longitude = getDouble(getColumnIndex(DropDbSchema.DropTable.Colums.LONGITUDE))
        val latitude = getDouble(getColumnIndex(DropDbSchema.DropTable.Colums.LATITUDE))
        val dropMessage = getString(getColumnIndex(DropDbSchema.DropTable.Colums.DROP_MESSAGE))
        val markerColor = getString(getColumnIndex(DropDbSchema.DropTable.Colums.MARKER_COLOR))
        return Drop(LatLng(latitude, longitude), dropMessage, id)
    }
}