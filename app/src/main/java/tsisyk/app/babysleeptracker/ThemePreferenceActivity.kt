@file:Suppress("DEPRECATION")

package tsisyk.app.babysleeptracker

import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import android.os.Bundle
import android.preference.PreferenceActivity


class ThemePreferenceActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        findPreference("theme").onPreferenceChangeListener =
            RefershActivityOnPreferenceChangeListener(RESULT_CODE_THEME_UPDATED)
    }

    private inner class RefershActivityOnPreferenceChangeListener(private val resultCode: Int) :
        OnPreferenceChangeListener {

        override fun onPreferenceChange(p: Preference, newValue: Any): Boolean {
            setResult(resultCode)
            return true
        }
    }

    companion object {
        const val RESULT_CODE_THEME_UPDATED = 1
    }
}