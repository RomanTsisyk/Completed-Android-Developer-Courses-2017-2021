package com.hussein.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import  kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    protected fun BuClickEvent(view: View) {
        val userDOB: String = etDOB.text.toString()
        val year: Int = Calendar.getInstance().get(Calendar.YEAR)
        val myAge = year - userDOB.toInt()
        tvShowAge.text = getString(R.string.age) + myAge
    }

}
