package com.example.firstapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PrimeNumbersService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendBroadcast(
            Intent("calculate").putExtra(
                "n",
                findNPrimeNumber(intent?.getIntExtra("n", 1) ?: 1)
            )
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented cause not needed now")
    }

    private fun findNPrimeNumber(n: Int): Int {
        var counter = 0
        var i = 1
        while (counter < n) {
            i++
            if (isPrime(i)) {
                counter++
            }
        }
        return i
    }

    private fun isPrime(n: Int): Boolean {
        var flag = true
        var i = 2
        while (i <= n / 2) {
            if (n % i == 0) {
                flag = false
                break
            }
            i++
        }
        return flag
    }
}