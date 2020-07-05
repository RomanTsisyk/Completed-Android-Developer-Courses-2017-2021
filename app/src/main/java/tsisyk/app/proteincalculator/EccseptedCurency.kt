package tsisyk.app.proteincalculator

sealed class Food {
    abstract val proteinValue: Double

    var amount: Double = 0.0

    val name: String
        get() = when (this) {
            is Eggs -> "Eggs"
            is Cheese -> "Cheese"
            is Sausage -> "Sausage"
        }

    fun totalProteinValue(): Double {
        return amount * proteinValue
    }
}

class Eggs : Food() {
    override val proteinValue = 0.24
}

class Sausage : Food() {
    override val proteinValue = 0.17
}

class Cheese : Food() {
    override val proteinValue = 0.22
}