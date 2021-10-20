package com.example.aidl_sample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var num1: EditText
    private lateinit var num2: EditText
    private lateinit var result: TextView
    private lateinit var calculate: Button

    lateinit var myInterface: CalculateAIDLInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        result = findViewById(R.id.result)
        calculate = findViewById(R.id.calculate)

        calculate.setOnClickListener(this)

        val calculateService = Intent(this, CalculateAIDLService::class.java)
        //Now, when a client (such as an activity) calls bindService() to connect to this service,
        // the client's onServiceConnected() callback receives the binder instance returned by the service's onBind() method.
        bindService(calculateService, myServiceConnection,Context.BIND_AUTO_CREATE)

    }

    ////anonymous inner class to connect client to service using onBind()
    private val myServiceConnection: ServiceConnection = object : ServiceConnection {
        // Following the example above for an AIDL interface,
        // this gets an instance of the IRemoteInterface, which we can use to call on the service
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            myInterface = CalculateAIDLInterface.Stub.asInterface(service)//cast the returned parameter to YourServiceInterface type.
        }
        // Called when the connection with the service disconnects unexpectedly
        override fun onServiceDisconnected(name: ComponentName) {
            Log.i("kajal", "Service disconnected")
//            myInterface = null
        }
    }

    override fun onClick(p0: View?) {
       var num1=(num1.text.toString()).toInt()
       var num2=(num2.text.toString()).toInt()
        result.text = myInterface.addTwoNumbers(num1,num2).toString()
    }
}