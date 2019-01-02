package tsisyk.app.hyspnosisforgoods

import java.util.*

val random = Random()

fun rand(from: Int, to: Int): Int {
  return random.nextInt(to - from) + from
}