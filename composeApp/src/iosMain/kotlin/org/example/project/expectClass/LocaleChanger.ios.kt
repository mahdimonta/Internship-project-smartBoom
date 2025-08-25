@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.example.project.expectClass


import platform.Foundation.NSUserDefaults


actual fun getCurrentLanguage(): String {
    val languages = NSUserDefaults.standardUserDefaults.arrayForKey("AppleLanguages") as? List<String>
    return languages?.firstOrNull() ?: "en"
}

actual fun changeLanguage(language: String) {
    NSUserDefaults.standardUserDefaults.setObject(listOf(language), forKey = "AppleLanguages")
    NSUserDefaults.standardUserDefaults.synchronize()
}