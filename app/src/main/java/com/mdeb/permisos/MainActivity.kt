package com.mdeb.permisos

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocketException
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

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

        // 1 - Obtener el adaptador Bluetooth, usando el BluetoothManager
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        if (bluetoothAdapter == null) {
            Log.d(TAG, "Bad news: Bluetooth Adapter is null")
        } else {
            Log.d(TAG, "Good news: Bluetooth Adapter is not null")
        }

        // 2 - Habilitar el Bluetooth

        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        val REQUEST_ENABLE_BT : Int = 0
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Log.d(TAG, "BLUETOOTH_CONNECT is not PERMISSION_GRANTED")

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)

            //return
        } else {
            Log.d(TAG, "BLUETOOTH_CONNECT is PERMISSION_GRANTED")
        }


        try
        {
            val isEnabled = bluetoothAdapter?.isEnabled

            Log.d(TAG,"BluetoothAdapter is enabled: $isEnabled")

        } catch (e:Exception) {
            Log.d(TAG, e.message.toString())
        }

        /*
        if (bluetoothAdapter?.isEnabled == false) {

            Log.d(TAG, "BluetoothAdapter is not enabled")


            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            val REQUEST_ENABLE_BT : Int = 0
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                Log.d(TAG, "BLUETOOTH_CONNECT is not PERMISSION_GRANTED")

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)

                //return
            } else {
                Log.d(TAG, "BLUETOOTH_CONNECT is PERMISSION_GRANTED")
            }

        } else{
            Log.d(TAG, "BluetoothAdapter is enabled")
        }
        */

    }


}