package tsisyk.app.babysleeptracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.preference.PreferenceManager
import android.content.SharedPreferences


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     /*   val pref = PreferenceManager
            .getDefaultSharedPreferences(this)
        val themeName = pref.getString("theme", "Theme1")
        if (themeName == "Theme1") {
            setTheme(R.style.Theme1)
        } else if (themeName == "Theme2") {
            Toast.makeText(this, "set theme", Toast.LENGTH_SHORT).show()
            setTheme(R.style.Theme2)
        }
        Toast.makeText(
            this, "Theme has been reset to " + themeName!!,
            Toast.LENGTH_SHORT
        ).show()*/
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
            startActivityForResult(new Intent(this,
                ThemePreferenceActivity.class), SETTINGS_ACTION);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_ACTION) {
            if (resultCode == ThemePreferenceActivity.RESULT_CODE_THEME_UPDATED) {
                finish();
                startActivity(getIntent());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
