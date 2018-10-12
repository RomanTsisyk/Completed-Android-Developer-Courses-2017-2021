package com.raywenderlich.android.ui

import android.arch.lifecycle.ViewModel
import com.raywenderlich.android.greetings.model.GreetingStore
import com.raywenderlich.android.greetings.model.Language

class GreetingsViewModel : ViewModel() {
    var greetingCount = 0
        private set

    var showFormal = true
        private set

    var language = GreetingStore.defaultLanguage
        private set

    val languages: List<Language> by lazy { GreetingStore.languages }

    fun updateGreeting(language: Language, callback: () -> Unit) {
        this.language = language
        greetingCount++
        callback()
    }

    fun updateShowFormal (showFormal: Boolean){
        this.showFormal = showFormal
    }

    fun greeting() = if (showFormal) language.greeting.formal else language.greeting.informal
}