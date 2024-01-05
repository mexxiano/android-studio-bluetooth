package com.mdeb.permisos

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothReceiver: BroadcastReceiver() {

    val TAG = "bluetoothReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if(action == BluetoothAdapter.ACTION_STATE_CHANGED){
            when(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)){
                BluetoothAdapter.STATE_ON->{
                    Log.d(TAG,"State On")
                }
                BluetoothAdapter.STATE_OFF ->{
                    Log.d(TAG,"State Off")
                }
                BluetoothAdapter.STATE_TURNING_OFF->{
                    Log.d(TAG,"Turning Off")
                }
                BluetoothAdapter.STATE_TURNING_ON->{
                    Log.d(TAG,"Turning On")
                }
            }
        }
    }
}