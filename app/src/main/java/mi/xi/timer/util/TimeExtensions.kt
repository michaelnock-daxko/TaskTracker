package mi.xi.timer.util

fun Long.timeHours(): Long {
    val seconds = this / 1000
    val minutes = seconds / 60
    return minutes / 60
}

fun Long.timeMinutes(): Long {
    val seconds = this / 1000
    val minutes = seconds / 60
    return minutes % 60
}

fun Long.timeSeconds(): Long {
    val seconds = this / 1000
    return seconds % 60
}

fun Long.timeMillis(): Long {
    return this % 1000
}

fun Long.zeroPadded() = "$this".padStart(2, '0').take(2)

fun Long.toTimeString(): String {
    val roundedMillis = this + 500

    val h = roundedMillis.timeHours()
    val hours = if (h > 0)"${h}h " else ""
    val minutes = roundedMillis.timeMinutes().zeroPadded()
    val seconds = roundedMillis.timeSeconds().zeroPadded()
    return "$hours${minutes}m ${seconds}s"
}
