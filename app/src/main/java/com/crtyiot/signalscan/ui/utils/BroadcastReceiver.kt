package com.crtyiot.signalscan.ui.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class ScanReceiver(private val onScanReceived: (String) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // for Seuic settings
        val scanData = intent?.getStringExtra("eee")
        //for zebra pda datawedge
        // val scanData = intent?.getStringExtra("com.symbol.datawedge.data_string")
        scanData?.let { onScanReceived(it) }
    }

    companion object {
        fun register(context: Context, onScanReceived: (String) -> Unit): ScanReceiver {
            val receiver = ScanReceiver(onScanReceived)
            context.registerReceiver(receiver, IntentFilter("com.v.b"))
            return receiver
        }
    }
}
