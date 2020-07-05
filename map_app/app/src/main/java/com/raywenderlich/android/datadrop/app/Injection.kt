package com.raywenderlich.android.datadrop.app

import com.raywenderlich.android.datadrop.model.*

object Injection {

   fun provideDropRepository(): DropRepository = RoomRepository()


}