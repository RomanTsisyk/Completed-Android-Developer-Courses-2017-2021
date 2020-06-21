package com.raywenderlich.android.datadrop.viewmodel

import androidx.lifecycle.ViewModel
import com.raywenderlich.android.datadrop.app.DataDropApplication

class MarkerColorViewModel : ViewModel() {

    private val markerColorDao = DataDropApplication.database.markerDao()
    private val allMarkerColors = markerColorDao.getAll()

    fun getMarkerColors() = allMarkerColors

}