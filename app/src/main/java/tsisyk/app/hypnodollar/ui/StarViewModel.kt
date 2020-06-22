package tsisyk.app.hypnodollar.ui

import android.media.Image
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_star.*
import tsisyk.app.hypnodollar.model.Star
import tsisyk.app.hypnodollar.model.Vector
import tsisyk.app.hypnodollar.utils.rand
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.max

class StarViewModel : ViewModel() {

    private var sizeX = 0.0
    private var sizeY = 0.0
    private var origin: Vector = Vector(sizeX / 2.0, sizeY / 2.0)
    private var starVectorMagnitude = max(sizeX, sizeY)
    private var timer = Timer("Stars", false)

    val starLiveData = MutableLiveData<Star>()
    val emittingLiveData = MutableLiveData<Boolean>()

    fun startEmittingStars() {
        emittingLiveData.postValue(true)
        // Emit a new star every 20 milliseconds
        timer.schedule(0, 20) {
            // Choose random start point
            val x = rand(0, sizeX.toInt()).toDouble()
            val y = rand(0, sizeY.toInt()).toDouble()
            val starEndPosition = calculateStarEndPosition(x, y)

            val star = Star(x.toInt(), y.toInt(), starEndPosition.x.toInt(), starEndPosition.y.toInt())

            starLiveData.postValue(star)
        }

    }

    fun stopEmittingStars() {
        emittingLiveData.postValue(false)
        timer.cancel()
        timer = Timer("Stars", false)
    }

    fun setupDisplay(sizeX: Double, sizeY: Double) {
        this.sizeX = sizeX
        this.sizeY = sizeY
        origin = Vector(sizeX / 2.0, sizeY / 2.0)
        starVectorMagnitude = max(sizeX, sizeY)
    }

    private fun calculateStarEndPosition(x: Double, y: Double): Vector {
        // Get end position as vector from origin of magnitude starVectorMagnitude
        // and in the same direction as (x, y).
        // If unitVector is null, then just use original position.
        val position = Vector(x, y) - origin
        val unitVector = position.unitVector()
        return if (unitVector != null) {
            unitVector * starVectorMagnitude + origin
        } else {
            position + origin
        }
    }
}