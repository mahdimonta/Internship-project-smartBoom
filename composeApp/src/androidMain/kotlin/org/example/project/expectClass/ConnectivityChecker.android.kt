package org.example.project.expectClass

// androidMain/ConnectivityChecker.kt
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("StaticFieldLeak")
lateinit var expectContext: Context

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ConnectivityChecker {
    actual suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
        val connectivityManager =
            expectContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return@withContext false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return@withContext false

        if (!capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) return@withContext false

        // حالا پینگ واقعی
        try {
            val url = URL("https://clients3.google.com/generate_204")
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 1000
            connection.connect()
            connection.responseCode == 204
        } catch (e: IOException) {
            false
        }
    }
}