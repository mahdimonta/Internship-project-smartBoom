package org.example.project.expectClass

import android.annotation.SuppressLint
import android.app.Activity
import kotlin.system.exitProcess

@SuppressLint("StaticFieldLeak")
lateinit var currentActivity: Activity

actual fun exitApp() {
    currentActivity.finishAffinity()
    exitProcess(0)
}

