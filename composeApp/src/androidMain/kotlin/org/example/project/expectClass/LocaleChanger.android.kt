@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.example.project.expectClass


import java.util.Locale
import android.content.res.Resources
import android.content.res.Configuration

actual fun getCurrentLanguage(): String = Locale.getDefault().language

actual fun changeLanguage(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    Resources.getSystem().updateConfiguration(config, Resources.getSystem().displayMetrics)
}