package com.forkaton.pemob7_123140020.platform

import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceBatteryState

actual class BatteryInfo actual constructor() {
    init {
        UIDevice.currentDevice.batteryMonitoringEnabled = true
    }

    actual fun getBatteryLevel(): Int {
        return (UIDevice.currentDevice.batteryLevel * 100).toInt()
    }

    actual fun isCharging(): Boolean {
        val state = UIDevice.currentDevice.batteryState
        return state == UIDeviceBatteryState.UIDeviceBatteryStateCharging || 
               state == UIDeviceBatteryState.UIDeviceBatteryStateFull
    }
}