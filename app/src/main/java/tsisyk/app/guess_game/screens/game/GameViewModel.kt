package tsisyk.app.guess_game.screens.game

import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import tsisyk.app.guess_game.R.drawable.*
import kotlin.collections.mutableListOf as mutableListOf1


private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

@Suppress("DEPRECATION")
class GameViewModel : ViewModel() {

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    companion object {
        private const val DONE = 0L
        private const val COUNTDOWN_PANIC_SECONDS = 10L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 300000L
    }

    private val timer: CountDownTimer
    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString: LiveData<String> = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val _image = MutableLiveData<Int>()
    val image: LiveData<Int>
        get() = _image


    private val _word = MutableLiveData<Int>()

    val word: LiveData<Int>
        get() = _word


    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private lateinit var imageList: MutableList<Int>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    init {
        resetImage()
        nextImage()
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
                if (millisUntilFinished / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS) _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventBuzz.value = BuzzType.GAME_OVER
                _eventGameFinish.value = true
            }
        }

        timer.start()
    }

    private fun nextImage() {
        if (imageList.isEmpty()) resetImage()
        _image.value = imageList.removeAt(0)
        _word.value = _image.value
        val  int =  _image.value

        Log.i("image", "$int")
    }

    fun onSkip() {
        nextImage()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        _eventBuzz.value = BuzzType.CORRECT
        nextImage()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun resetImage() {

        imageList = mutableListOf1(
           poster1 , poster2 , poster3 , poster4 , poster5 , poster6 , poster7 , poster8 , poster9 , poster10 , poster11 , poster12 , poster13 , poster14 , poster15 , poster16 , poster17 , poster18 , poster19 , poster20 , poster21 , poster22 , poster23 , poster24 , poster25 , poster26 , poster27 , poster28 , poster29 , poster30 , poster31 , poster32 , poster33 , poster34 , poster35 , poster36 , poster37 , poster38 , poster39 , poster40 , poster41 , poster42 , poster43 , poster44 , poster45 , poster46 , poster47 , poster48 , poster49 , poster50 , poster51 , poster52 , poster53 , poster54 , poster55 , poster56 , poster57 , poster58 , poster59 , poster60 , poster61 , poster62 , poster63 , poster64 , poster65 , poster66 , poster67 , poster68 , poster69 , poster70 , poster71 , poster72 , poster73 , poster74 , poster75 , poster76 , poster77 , poster78 , poster79 , poster80 , poster81 , poster82 , poster83 , poster84 , poster85 , poster86 , poster87 , poster88 , poster89 , poster90 , poster91 , poster92 , poster93 , poster94 , poster95 , poster96 , poster97 , poster98 , poster99 , poster100 , poster101 , poster102 , poster103 , poster104 , poster105 , poster106 , poster107 , poster108 , poster109 , poster110 , poster111 , poster112 , poster113 , poster114 , poster115 , poster116 , poster117 , poster118 , poster119 , poster120 , poster121 , poster122 , poster123 , poster124 , poster125 , poster126 , poster127 , poster128 , poster129 , poster130 , poster131 , poster132 , poster133 , poster134 , poster135 , poster136 , poster137 , poster138 , poster139 , poster140 , poster141 , poster142 , poster143 , poster144 , poster145 , poster146 , poster147 , poster148 , poster149 , poster150 , poster151 , poster152 , poster153 , poster154 , poster155 , poster156 , poster157 , poster158 , poster159 , poster160 , poster161 , poster162 , poster163 , poster164 , poster165 , poster166 , poster167 , poster168 , poster169 , poster170 , poster171 , poster172 , poster173 , poster174 , poster175 , poster176 , poster177 , poster178 , poster179 , poster180 , poster181 , poster182 , poster183 , poster184 , poster185 , poster186 , poster187 , poster188 , poster189 , poster190 , poster191 , poster192 , poster193 , poster194 , poster195 , poster196 , poster197 , poster198 , poster199 , poster200 , poster201 , poster202 , poster203 , poster204 , poster205 , poster206 , poster207 , poster208 , poster209 , poster210 , poster211 , poster212 , poster213 , poster214 , poster215 , poster216 , poster217 , poster218 , poster219 , poster220 , poster221 , poster222 , poster223 , poster224 , poster225 , poster226 , poster227 , poster228 , poster229 , poster230 , poster231 , poster232 , poster233 , poster234 , poster235 , poster236 , poster237 , poster238 , poster239 , poster240 , poster241 , poster242 , poster243 , poster244 , poster245 , poster246 , poster247 , poster248 , poster249 , poster250 , poster251 , poster252 , poster253 , poster254 , poster255 , poster256 , poster257 , poster258 , poster259 , poster260 , poster261 , poster262 , poster263 , poster264 , poster265

        )
        imageList.shuffle()
    }

}

