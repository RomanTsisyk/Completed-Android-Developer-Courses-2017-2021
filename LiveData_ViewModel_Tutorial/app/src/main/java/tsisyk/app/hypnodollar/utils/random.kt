package tsisyk.app.hypnodollar.utils

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_star.*
import tsisyk.app.hypnodollar.ui.StarActivity
import java.util.*

val random = Random()

fun rand(from: Int, to: Int): Int {
  return random.nextInt(to - from) + from
}