package org.example.project.expectClass

import platform.posix.exit

actual fun exitApp() {
    exit(0)
}