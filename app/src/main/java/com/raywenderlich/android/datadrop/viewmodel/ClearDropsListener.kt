package com.raywenderlich.android.datadrop.viewmodel

import com.raywenderlich.android.datadrop.model.Drop

interface ClearDropsListener {

    fun dropsCleared(drop: Drop)
}