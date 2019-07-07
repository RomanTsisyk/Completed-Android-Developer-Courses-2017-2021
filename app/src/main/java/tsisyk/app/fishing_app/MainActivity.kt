package tsisyk.app.fishing_app

import android.content.ActivityNotFoundException
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LifecycleObserver
import timber.log.Timber
import tsisyk.app.fishing_app.databinding.ActivityMainBinding
import android.view.animation.AnimationUtils
import kotlin.random.Random


const val KEY_REVENUE = "revenue_key"
const val KEY_FISHES = "fishes_key"
const val KEY_TIMER_SECONDS = "timer_seconds_key"

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private var revenue = 0
    private var fishGeted = 0
    private var l = Int

    private lateinit var timer: Timer
    private lateinit var binding: ActivityMainBinding

    data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int)

    private val allFishes = listOf(
        Dessert(R.drawable.fish1, 5, 0),
        Dessert(R.drawable.fish2, 5, 5),
        Dessert(R.drawable.fish3, 2, 25),
        Dessert(R.drawable.fish4, 6, 35),
        Dessert(R.drawable.fish5, 3, 45),
        Dessert(R.drawable.fish6, 7, 55),
        Dessert(R.drawable.fish7, 5, 65),
        Dessert(R.drawable.fish8, 9, 75),
        Dessert(R.drawable.fish9, 4, 85),
        Dessert(R.drawable.fish10, 7, 95),
        Dessert(R.drawable.fish11, 5, 105),
        Dessert(R.drawable.fish11, 4, 120),
        Dessert(R.drawable.fish12, 1, 135)
    )
    private var currentFish = allFishes[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate Called")
        binding = setContentView(this, R.layout.activity_main)
        binding.fish.setOnClickListener {
            onFishClicked()
        }

        timer = Timer(this.lifecycle)

        if (savedInstanceState != null) {
            // Get all the game state information from the bundle, set it
            revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
            fishGeted = savedInstanceState.getInt(KEY_FISHES, 0)
            timer.secondsCount = savedInstanceState.getInt(KEY_TIMER_SECONDS, 30)
            showCurrentFish()

        }

        binding.revenue = revenue
        binding.amountFishes = fishGeted
        binding.fish.setImageResource(currentFish.imageId)

  //      binding.timerCount.setText(timer.secondsCount)
    }


    private fun onFishClicked() {
        revenue += currentFish.price
        fishGeted++
        binding.revenue = revenue
        binding.amountFishes = fishGeted
        showCurrentFish()
    }


    private fun showCurrentFish() {
        var newFish = allFishes[0]
        for (fishes in allFishes) {
            if (fishGeted >= fishes.startProductionAmount) {
                newFish = fishes
            } else break
        }
        if (newFish != currentFish) {
            currentFish = newFish
            binding.fish.setImageResource(newFish.imageId)
        }
        val animation = AnimationUtils.loadAnimation(this, R.anim.changing)
        binding.fish.startAnimation(animation)
    }

    private fun locateView(v: View?): Rect? {
        val loc_int = IntArray(2)
        if (v == null) return null
        try {
            v!!.getLocationOnScreen(loc_int)
        } catch (npe: NullPointerException) {
            //Happens when the view doesn't exist on screen anymore.
            return null
        }

        val location = Rect()
        location.left = loc_int[0]
        location.top = loc_int[1]
        location.right = location.left + v!!.width
        location.bottom = location.top + v!!.height
        return location
    }


    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(getString(R.string.share_text, fishGeted, revenue))
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenuButton -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_REVENUE, revenue)
        outState.putInt(KEY_FISHES, fishGeted)
        outState.putInt(KEY_TIMER_SECONDS, timer.secondsCount)
        Timber.i("onSaveInstanceState Called")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState Called")
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }
}