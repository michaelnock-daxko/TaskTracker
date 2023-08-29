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
    val h = timeHours()
    val hours = if (h > 0)"${timeHours()}:" else ""
    val minutes = timeMinutes().zeroPadded()
    val seconds = timeSeconds().zeroPadded()
    val millis = timeMillis().zeroPadded()
    return "$hours$minutes:$seconds:$millis"
}
