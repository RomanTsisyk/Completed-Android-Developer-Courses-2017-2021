package tsisyk.app.proteincalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_converter.*


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

            proteinValue.text = String.format("$%.2f", result.totalProteinValue())
        }
    }


    fun currencyFromSelection() =
            when (food[foodSpinner.selectedItemPosition]) {
                is Eggs -> Eggs()
                is Sausage -> Sausage()
                is Cheese -> Cheese()

            }
}
