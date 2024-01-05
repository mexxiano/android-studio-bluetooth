package com.mdeb.permisos

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG:String = "PERMISOS_APP"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check to see if the Bluetooth classic feature is available.
        val bluetoothAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)

        // Check to see if the BLE feature is available.
        val bluetoothLEAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

        Log.d(TAG, "Bluetooth Available: $bluetoothAvailable")
        Log.d(TAG, "Bluetooth LE Available: $bluetoothLEAvailable")

        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        if (bluetoothAdapter == null) {
            Log.d(TAG, "Bad news: Bluetooth Adapter is null")
        } else {
            Log.d(TAG, "Good news: Bluetooth Adapter is not null")
        }
    }


}