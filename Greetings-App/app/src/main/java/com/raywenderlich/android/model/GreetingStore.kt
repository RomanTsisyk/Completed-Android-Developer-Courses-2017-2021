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

package com.raywenderlich.android.greetings.model

import org.json.JSONObject


object GreetingStore {

  private const val languageData = "{\"languages\":[{\"name\": \"Arabic\", \"greeting\": {\"formal\": \"Asalaam alaikum\", \"informal\": \"Ahlan\"}},{\"name\": \"Chinese\", \"greeting\": {\"formal\": \"Nǐn hǎo\", \"informal\": \"Nǐ hǎo\"}},{\"name\": \"English\", \"greeting\": {\"formal\": \"Hello\", \"informal\": \"Hi\"}},{\"name\": \"French\", \"greeting\": {\"formal\": \"Bonjour\", \"informal\": \"Salut\"}},{\"name\": \"German\", \"greeting\": {\"formal\": \"Guten Tag\", \"informal\": \"Hallo\"}},{\"name\": \"Italian\", \"greeting\": {\"formal\": \"Salve\", \"informal\": \"Ciao\"}},{\"name\": \"Japanese\", \"greeting\": {\"formal\": \"Konnichiwa\", \"informal\": \"Yā, _Yō\"}},{\"name\": \"Korean\", \"greeting\": {\"formal\": \"Anyoung haseyo\", \"informal\": \"Anyoung\"}},{\"name\": \"Portuguese\", \"greeting\": {\"formal\": \"Olá\", \"informal\": \"Oi\"}},{\"name\": \"Russian\", \"greeting\": {\"formal\": \"Zdravstvuyte\", \"informal\": \"Privet\"}},{\"name\": \"Spanish\", \"greeting\": {\"formal\": \"Hola\", \"informal\": \"¿Qué tal?\"}}]}"

  val languages: List<Language> by lazy {
    val languages = mutableListOf<Language>()
    val languageArray = JSONObject(languageData).getJSONArray("languages")

    for (i in 0 until languageArray.length()) {
      val languageObject = languageArray[i] as JSONObject
      val greetingObject = languageObject.get("greeting") as JSONObject
      val language = Language(languageObject.getString("name"),
          Greeting(greetingObject.getString("formal"), greetingObject.getString("informal")))
      languages.add(language)
    }

    languages
  }

  val defaultLanguage = Language("English", Greeting("Hello", "Hi"))
}