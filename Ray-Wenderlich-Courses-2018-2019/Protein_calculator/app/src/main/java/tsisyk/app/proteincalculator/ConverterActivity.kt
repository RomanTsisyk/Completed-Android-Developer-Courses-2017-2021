package tsisyk.app.proteincalculator

import android.animation.ValueAnimator
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_converter.*
import android.widget.TextView
import java.io.IOException
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.Toast


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

        imageView.setOnClickListener {
            showDialog()
        }
    }

    private fun move(view: TextView) {
        val animator = ValueAnimator.ofFloat(0f, 10f)
        animator.duration = 300
        animator.addUpdateListener { animation -> view.translationX = animation.animatedValue as Float }
        animator.repeatCount = 5
        animator.start()

    }


    private fun currencyFromSelection() =
            when (food[foodSpinner.selectedItemPosition]) {
                is Eggs -> Eggs()
                is Sausage -> Sausage()
                is Cheese -> Cheese()

            }


    fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.rate_us)
                .setPositiveButton(R.string.ok,
                        DialogInterface.OnClickListener { _, _ ->
                            val openIntent = Intent(android.content.Intent.ACTION_VIEW)
                            openIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
                            startActivity(openIntent)
                        })
                .setNegativeButton(R.string.no,
                        DialogInterface.OnClickListener { _, _ ->
                        })
        // Create the AlertDialog object and return it
        builder.create()

    }
}


//    fun animationIn(view: View) {
//        val animationIn = AnimationUtils.loadAnimation(this, R.anim.animation_in)
//        animationIn.duration = 300
//        animationIn.repeatCount = 5
//        animationIn.start()
//    }
//
//    fun animationOut(view: View) {
//        val animationIn = AnimationUtils.loadAnimation(this, R.anim.animation_out)
//        animationIn.duration = 300
//        animationIn.repeatCount = 5
//        animationIn.start()
//    }
//
//    @Throws(IOException::class)
//    private fun decodeImage() {
//        val decodedAnimation = ImageDecoder.decodeDrawable(
//                ImageDecoder.createSource(resources, R.drawable.fitball))
//
//        // Prior to start(), the first frame is displayed.
//        (decodedAnimation as? AnimatedImageDrawable)?.start()
//    }

