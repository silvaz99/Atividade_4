package com.example.atividade4

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat

class LocationSensor(applicationContext: Context) : LocationListener {
    var context = applicationContext;

    public fun getLocation(): Location? {
        val lm: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10f, this)
        return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }

    override fun onLocationChanged(p0: Location) {
        //TODO("Not yet implemented")
        Log.d("GPS", "GPS: $p0")
    }
}