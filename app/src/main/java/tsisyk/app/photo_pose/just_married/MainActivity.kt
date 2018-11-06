package tsisyk.app.photo_pose.just_married

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val drawableResource = when (Random.nextInt(24) + 1) {
            1 -> R.drawable.pose01
            2 -> R.drawable.pose02
            3 -> R.drawable.pose03
            4 -> R.drawable.pose04
            5 -> R.drawable.pose05
            6 -> R.drawable.pose06
            7 -> R.drawable.pose07
            8 -> R.drawable.pose08
            9 -> R.drawable.pose09
            10 -> R.drawable.pose10
            11 -> R.drawable.pose11
            12 -> R.drawable.pose12
            13 -> R.drawable.pose13
            14 -> R.drawable.pose14
            15 -> R.drawable.pose15
            16 -> R.drawable.pose16
            17 -> R.drawable.pose17
            18 -> R.drawable.pose18
            19 -> R.drawable.pose19
            20 -> R.drawable.pose20
            21 -> R.drawable.pose21
            22 -> R.drawable.pose22
            23 -> R.drawable.pose23
            24 -> R.drawable.pose24

            else -> R.drawable.empty_dice
        }

        val diceImage : ImageView = findViewById(R.id.dice_image)
        diceImage.setImageResource(drawableResource)

    }
}
