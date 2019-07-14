package tsisyk.app.wctime

import android.annotation.SuppressLint
import android.content.res.Resources
import java.text.SimpleDateFormat


fun convertNumericQualityToString(quality: Int, resources: Resources): String {
    var qualityString = resources.getString(R.string.three_ok)
    when (quality) {
        -1 -> qualityString = resources.getString(R.string.one_poor)
        0 -> qualityString = resources.getString(R.string.two_soso)
        1 -> qualityString = resources.getString(R.string.five_excellent)

    }
    return qualityString
}


@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(systemTime).toString()
}
