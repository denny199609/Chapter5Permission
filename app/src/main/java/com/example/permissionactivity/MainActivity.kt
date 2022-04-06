package com.example.permissionactivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.permissionactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnShowImage.setOnClickListener {
                loadImage()
            }
            btnNext.setOnClickListener {
                checkPermission()
            }
        }
    }

    private fun loadImage() = binding.run {
        Glide.with(this@MainActivity)
            .load(Constant.IMAGE_URL)
            .placeholder(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(image1)
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this, SecondActivity::class.java)
                    .putExtra("Location",getLatLong()))
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 201)
            }
        } else {
            Toast.makeText(this, "Your app is not supported", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 201){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION){
                Toast.makeText(this, "PERMISSION Granted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SecondActivity::class.java)
                    .putExtra("Location",getLatLong()))
            }else{
                Toast.makeText(this, "PERMISSION Declined", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLatLong():String {
        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var location : Location? = null
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        val latlong = "Lat : ${location?.latitude}, Long : ${location?.longitude}"
        return latlong
    }
}