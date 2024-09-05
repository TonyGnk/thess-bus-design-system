package com.tonyGnk.thessBus.designSystem.mobile.utils

import android.annotation.SuppressLint
import android.content.Context
import java.util.Locale

@SuppressLint("ObsoleteSdkInt")
fun isLanguageGreek(context: Context): Boolean {
    val locale: Locale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0]
    } else {
        @Suppress("DEPRECATION")
        context.resources.configuration.locale
    }
    return locale.language == "el"
}
