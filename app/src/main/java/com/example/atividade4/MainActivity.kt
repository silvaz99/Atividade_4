package com.example.atividade4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager : SensorManager? = null
    private var light : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)


        val button : Button = findViewById(R.id.button)
        val textViewLatitude : TextView = findViewById(R.id.textView4)
        val textViewLongitude : TextView = findViewById(R.id.textView5)

        button.setOnClickListener {
            val ls = LocationSensor(applicationContext)
            val l: Location? = ls.getLocation()
            val lat = l?.latitude
            val long = l?.longitude

            textViewLongitude.text = long.toString()
            textViewLatitude.text = lat.toString()
        }

        sensorManager!!.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        setContentView(R.layout.activity_main)

        val lightTextView : TextView = findViewById(R.id.textView)
        Log.d("Light: ", event?.values?.get(0).toString())
        if (event != null) {
            lightTextView.text = "Luz: " + event.values[0].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}