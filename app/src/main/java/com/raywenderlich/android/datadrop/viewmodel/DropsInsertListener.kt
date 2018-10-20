package com.raywenderlich.android.datadrop.viewmodel

import com.raywenderlich.android.datadrop.model.Drop

interface DropsInsertListener {

    fun dropsInserted(drop: Drop)
}