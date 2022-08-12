package ken.projects.infit.util

import kotlin.math.roundToInt

fun getTimeStringFromDouble(timeElapsed: Double): String {

    val resultInt = timeElapsed.roundToInt()
    val hours = resultInt % 86400 / 3600
    val minutes = resultInt % 86400 % 3600 / 60
    val seconds = resultInt % 86400 % 3600 % 60

    return makeTimeString(hours, minutes, seconds)
}

fun makeTimeString(hours: Int, min: Int, sec: Int): String =
    String.format("%02d:%02d:%02d", hours, min, sec)
