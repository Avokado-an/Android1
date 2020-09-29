package com.example.firstapp.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.R
import com.example.firstapp.service.PrimeNumbersService

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("calculate" == intent.action) {
                val result = intent.getIntExtra("n", 1)
                showResult(result.toString())
                Log.d("BogdanovAM result ", result.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(broadcastReceiver, IntentFilter("calculate"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("counter", counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("counter") + 1
        Log.w("BogdanovAM counter ", counter.toString())
        startService(
            Intent(
                this, PrimeNumbersService::class.java
            ).putExtra("n", counter)
        )
    }

    private fun showResult(message: String) {
        val toastGreet = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toastGreet.show()
    }
}