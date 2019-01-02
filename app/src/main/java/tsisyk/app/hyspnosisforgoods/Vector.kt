package tsisyk.app.hyspnosisforgoods

import kotlin.math.sqrt


data class Vector(val x: Double, val y: Double) {

  private val magnitude: Double
    get() = sqrt(x*x + y*y)

  operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
  operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
  operator fun times(magnitude: Double): Vector {
    return Vector(x * magnitude, y * magnitude)
  }

  fun unitVector(): Vector? {
    if (magnitude > 0.0) {
      return Vector(x / magnitude, y / magnitude)
    }
    return null
  }
}