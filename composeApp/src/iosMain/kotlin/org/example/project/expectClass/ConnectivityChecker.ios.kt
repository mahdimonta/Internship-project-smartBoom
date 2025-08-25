package org.example.project.expectClass

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_status_satisfied
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.resume

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ConnectivityChecker {
    actual suspend fun isNetworkAvailable(): Boolean = suspendCancellableCoroutine { cont ->
        val monitor = nw_path_monitor_create()
        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())

        nw_path_monitor_set_update_handler(monitor) { path ->
            val hasInternet = nw_path_get_status(path) == nw_path_status_satisfied
            nw_path_monitor_cancel(monitor)
            cont.resume(hasInternet)
        }

        nw_path_monitor_start(monitor)
    }
}