@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.example.project.expectClass

import java.util.Locale

actual fun getCurrentLanguage(): String = Locale.getDefault().language

actual fun changeLanguage(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
}