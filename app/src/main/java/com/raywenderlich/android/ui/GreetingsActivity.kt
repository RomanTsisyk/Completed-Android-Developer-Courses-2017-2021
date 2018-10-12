/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.raywenderlich.android.greetings.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.raywenderlich.android.greetings.R
import com.raywenderlich.android.greetings.model.GreetingStore
import com.raywenderlich.android.greetings.model.Language
import com.raywenderlich.android.ui.GreetingsViewModel
import kotlinx.android.synthetic.main.activity_greetings.*


class GreetingsActivity : AppCompatActivity() {

    private var viewModel = GreetingsViewModel(application)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        configureSpinner()
        configureRadioGroup()

        viewModel = ViewModelProviders.of(this).get(GreetingsViewModel::class.java)

        showButton.setOnClickListener {
            viewModel.updateGreeting(languageSpinner.selectedItem as Language){
                configureGreeting()
            }
        }

        configureGreeting()
    }

    private fun configureSpinner() {
        languageSpinner.adapter = ArrayAdapter<Language>(this,
                android.R.layout.simple_spinner_dropdown_item, viewModel.languages)
        languageSpinner.setSelection(2)
    }

    private fun configureRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton = group.findViewById(checkedId) as RadioButton
            viewModel.updateShowFormal(checkedRadioButton.text == getString(R.string.formal))
        }
    }

    private fun configureGreeting() {

        greeting.text = viewModel.greeting()
        count.text = viewModel.countText()
    }
}
