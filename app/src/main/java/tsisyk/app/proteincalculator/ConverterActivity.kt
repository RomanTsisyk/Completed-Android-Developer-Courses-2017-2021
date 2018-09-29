package tsisyk.app.proteincalculator

import android.animation.ValueAnimator
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_converter.*
import android.widget.TextView
import java.io.IOException


class ConverterActivity : AppCompatActivity() {

    companion object {
        private val food = listOf(Eggs(), Sausage(), Cheese())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                food.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        foodSpinner.adapter = adapter

        convert.setOnClickListener {
            val result = currencyFromSelection()
            result.amount = resultAmount.text.toString().toDouble()
            resultAmount.text.toString().toDouble()
            proteinValue.text = String.format("%.2f grams", result.totalProteinValue())
            move(proteinValue)
        }

    }

    private fun move(view: TextView) {
        val animator = ValueAnimator.ofFloat(0f, 10f)
        val mDuration = 300 //in millis
        animator.duration = mDuration.toLong()
        animator.addUpdateListener { animation -> view.translationX = animation.animatedValue as Float }
        animator.repeatCount = 5
        animator.start()

        decodeImage()
    }

    @Throws(IOException::class)
    private fun decodeImage() {
        val decodedAnimation = ImageDecoder.decodeDrawable(
                ImageDecoder.createSource(resources, R.drawable.fitball))

        // Prior to start(), the first frame is displayed.
        (decodedAnimation as? AnimatedImageDrawable)?.start()
    }

    private fun currencyFromSelection() =
            when (food[foodSpinner.selectedItemPosition]) {
                is Eggs -> Eggs()
                is Sausage -> Sausage()
                is Cheese -> Cheese()

            }
}
