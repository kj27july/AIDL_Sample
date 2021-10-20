package com.example.aidl_sample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException


class CalculateAIDLService : Service() {

    override fun onBind(intent: Intent): IBinder {
        //return interface
        return  mBinder
    }
////expose interface to service
    private val mBinder = object : CalculateAIDLInterface.Stub() {
        @Throws(RemoteException::class)
        override fun addTwoNumbers(num1: Int, num2: Int): Int {
            return num1+num2
        }
    }

}