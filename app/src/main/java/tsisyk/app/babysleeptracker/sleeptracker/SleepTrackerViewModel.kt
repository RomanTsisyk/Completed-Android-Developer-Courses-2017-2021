package tsisyk.app.babysleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*
import tsisyk.app.babysleeptracker.database.SleepDatabaseDao
import tsisyk.app.babysleeptracker.database.SleepNight
import tsisyk.app.babysleeptracker.formatNights

class SleepTrackerViewModel(
    private val database: SleepDatabaseDao, application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var tonight = MutableLiveData<SleepNight?>()
     val nights = database.getAllNights()
    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    init {
        scopeInitTonight()
    }

    fun scopeInitTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onStartTracking() {
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
            startButton_showSnackbarEvent.value = true

        }
    }

    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)

            _navigateToSleepQuality.value = oldNight
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            clearButton_showSnackbarEvent.value = true

        }
    }

    private suspend fun insert(night: SleepNight){
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }!!


    val startButtonVisible = Transformations.map(tonight) {
        null == it
    }!!

    val stopButtonVisible = Transformations.map(tonight) {
        null != it
    }!!

    val clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }!!

    /// Clear button

    private var clearButton_showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEventClearButtonClicked: LiveData<Boolean>
        get() = clearButton_showSnackbarEvent

    fun doneShowingSnackbar() {
        clearButton_showSnackbarEvent.value = false
    }

    /// Start button

    private var startButton_showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEventStartbuttonClicked: LiveData<Boolean>
        get() = startButton_showSnackbarEvent

    fun doneShowingSnackbar2() {
        clearButton_showSnackbarEvent.value = false
    }
}


