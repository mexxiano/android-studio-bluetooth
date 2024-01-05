package com.mdeb.permisos

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocketException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    val TAG:String = "PERMISOS_APP"
    lateinit var bluetoothManger: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter

    lateinit var bluetoothReceiver: BluetoothReceiver
    lateinit var receiver2 : Discoverability
    lateinit var btnOnOff: Button
    lateinit var btnDiscoverable:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothReceiver = BluetoothReceiver()
        receiver2 = Discoverability()

        initUI()

        bluetoothManger = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManger.adapter

        enableDisableBT()
        enableDisableBTDiscoverability()

    }

    @SuppressLint("MissingPermission")
    private fun enableDisableBTDiscoverability() {
        when{
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_GRANTED ->{

            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.BLUETOOTH_ADVERTISE) ->{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_ADVERTISE), 101)
            }
        }

        btnDiscoverable.setOnClickListener {
            val discoverIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,20)
            startActivity(discoverIntent)

            val intentFilter = IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)
            registerReceiver(receiver2, intentFilter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bluetoothReceiver)
        unregisterReceiver(receiver2)
    }

    private fun initUI() {
        btnDiscoverable = findViewById(R.id.btnDiscoverability)
        btnOnOff = findViewById(R.id.btnOnOf)
    }

    @SuppressLint("MissingPermission")
    private fun enableDisableBT() {
        when{
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED ->{

            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.BLUETOOTH_CONNECT) ->{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT), 101)
            }
        }

        btnOnOff.setOnClickListener {
            if(!bluetoothAdapter.isEnabled){
                bluetoothAdapter.enable()
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(intent)

                val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
                registerReceiver(bluetoothReceiver, intentFilter)
            }

            if(bluetoothAdapter.isEnabled){
                bluetoothAdapter.disable()

                val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)

                registerReceiver(bluetoothReceiver, intentFilter)
            }
        }

    }

}