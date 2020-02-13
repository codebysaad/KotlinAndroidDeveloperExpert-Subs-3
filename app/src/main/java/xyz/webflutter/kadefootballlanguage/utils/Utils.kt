package xyz.webflutter.kadefootballlanguage.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FROM_API = "yyyy-MM-dd'T'hh:mm:ss Z"
const val DATE_MATCH = "EEEE, d MMMM yyyy"
const val DATE_FROM_API_2 = "yyyy-MM-dd'T'HH:mm:ss+00:00"
const val SOCCER_RESPONSE = "Soccer"

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun convertDate(date: String, time: String): String {

    val dateFromApi = try {
        SimpleDateFormat(DATE_FROM_API, Locale.getDefault()).parse("${date}T${time} GMT")
    } catch (e: Exception) {
        SimpleDateFormat(DATE_FROM_API_2, Locale.getDefault()).parse("${date}T${time}")
    }
    return SimpleDateFormat(DATE_MATCH, Locale.getDefault()).format(dateFromApi)
}