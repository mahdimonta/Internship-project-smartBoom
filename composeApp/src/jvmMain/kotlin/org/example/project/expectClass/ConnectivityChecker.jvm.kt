package org.example.project.expectClass


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ConnectivityChecker {
    actual suspend fun isNetworkAvailable(): Boolean {
        return try {
            val url = java.net.URL("https://www.google.com")
            val connection = url.openConnection() as java.net.HttpURLConnection
            connection.connectTimeout = 2000
            connection.connect()
            connection.responseCode == 200
        } catch (e: Exception) {
            false
        }
    }
}