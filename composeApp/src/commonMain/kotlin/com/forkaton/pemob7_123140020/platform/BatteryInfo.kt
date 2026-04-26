package com.forkaton.pemob7_123140020.platform

expect class BatteryInfo() {
    fun getBatteryLevel(): Int // Mengembalikan nilai 0-100
    fun isCharging(): Boolean
}