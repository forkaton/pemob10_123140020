package com.forkaton.pemob7_123140020

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform