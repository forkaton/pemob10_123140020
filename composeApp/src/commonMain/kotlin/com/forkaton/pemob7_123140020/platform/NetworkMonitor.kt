package com.forkaton.pemob7_123140020.platform

import kotlinx.coroutines.flow.Flow

// Deklarasi fungsi yang harus dimiliki oleh tiap platform
expect class NetworkMonitor {
    fun isConnected(): Boolean
    fun observeConnectivity(): Flow<Boolean>
}