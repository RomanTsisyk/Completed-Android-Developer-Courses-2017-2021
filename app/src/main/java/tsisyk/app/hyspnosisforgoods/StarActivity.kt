package tsisyk.app.hyspnosisforgoods

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_star.*


class StarActivity : AppCompatActivity() {

  private lateinit var starViewModel: StarViewModel

  private val starViews = mutableListOf<View>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_star)

    starViewModel = ViewModelProviders.of(this).get(StarViewModel::class.java)

    setupButtons()

    starViewModel.starLiveData.observe(this, Observer { animateStar(it) })
    starViewModel.emittingLiveData.observe(this, Observer {
      resetButton.isEnabled = it ?: false
      startButton.isEnabled = !resetButton.isEnabled
    })
  }

  private fun setupButtons() {
    startButton.setOnClickListener {
      starViewModel.startEmittingStars()
    }

    resetButton.setOnClickListener {
      starViewModel.stopEmittingStars()
      starViews.forEach { starField.removeView(it) }
      starViews.clear()
      resetButton.isEnabled = false
      startButton.isEnabled = true
    }
  }

  override fun onResume() {
    super.onResume()
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    starViewModel.setupDisplay(displayMetrics.widthPixels.toDouble(), displayMetrics.heightPixels.toDouble())
  }
  
  private fun animateStar(star: Star?) {
    if (star != null) {
      val starView = createStarView(star)

      val xAnimator = objectAnimatorOfFloat(starView, "x", star.x.toFloat(), star.endX.toFloat())
      val yAnimator = objectAnimatorOfFloat(starView, "y", star.y.toFloat(), star.endY.toFloat())

      starField.addView(starView)
      starViews.add(starView)

      AnimatorSet().apply {
        play(xAnimator).with(yAnimator)

        addListener(object : AnimatorListenerAdapter() {
          override fun onAnimationEnd(animation: Animator?) {
            starField.removeView(starView)
          }
        })

        start()
      }
    }
  }

  private fun createStarView(star: Star): View {
    val starView = View(this)
    val starSize = rand(MIN_SIZE, MAX_SIZE)
    starView.layoutParams = FrameLayout.LayoutParams(starSize, starSize)
    starView.setBackgroundResource(R.drawable.ic_dollar)
    starView.x = star.x.toFloat()
    starView.y = star.y.toFloat()
    starView.setBackgroundColor(Color.parseColor(STAR_COLOR))
    return starView
  }

  private fun objectAnimatorOfFloat(view: View, propertyName: String, startValue: Float, endValue: Float): ObjectAnimator {
    val animator = ObjectAnimator.ofFloat(view, propertyName, startValue, endValue)
    animator.interpolator = LinearInterpolator()
    animator.duration = DURATION
    return animator
  }

  companion object {
    private const val DURATION = 6000L
    private const val STAR_COLOR = "#FEDB41"
    private const val MIN_SIZE = 8
    private const val MAX_SIZE = 12
  }
}
