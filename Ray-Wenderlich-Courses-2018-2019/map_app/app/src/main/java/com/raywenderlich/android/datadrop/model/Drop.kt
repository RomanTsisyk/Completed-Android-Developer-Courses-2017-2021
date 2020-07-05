package com.raywenderlich.android.datadrop.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.util.*

@Entity(tableName = "drop_table", foreignKeys = [ForeignKey(entity = MarkerColor::class,
        parentColumns = ["displayString"],
        childColumns = ["markerColor"],
        onDelete = CASCADE)])
data class Drop(val latLng: LatLng,
                val dropMessage: String,
                @PrimaryKey
                val id: String = UUID.randomUUID().toString(),
                val markerColor: String = MarkerColor.RED_COLOR) {
    fun latLngString() = "%.6f, %.6f".format(latLng.latitude, latLng.longitude)
}