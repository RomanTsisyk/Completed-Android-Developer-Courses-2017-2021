package com.raywenderlich.android.datadrop.model


object DropDbSchema {
    const val VERSION = 2
    const val DB_NAME = "drop.db"

    object DropTable {
        const val NAME = "drops"

        object Colums {
            const val ID = "id"
            const val LATITUDE = "latitude"
            const val LONGITUDE = "longitude"
            const val DROP_MESSAGE = "dropMessage"
            const val MARKER_COLOR = "marker_color"
        }
    }
}