package com.raywenderlich.android.datadrop.model

import androidx.lifecycle.LiveData


interface DropRepository {
  fun addDrop(drop: Drop)
  fun getDrops(): LiveData<List<Drop>>
  fun clearDrop(drop: Drop)
  fun clearAllDrops()
}