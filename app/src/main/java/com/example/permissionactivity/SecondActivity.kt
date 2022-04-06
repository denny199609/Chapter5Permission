package com.example.permissionactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.permissionactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondBinding

    private val latlong by lazy{
        intent.getStringExtra("Location")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            imgBack.setOnClickListener { onBackPressed() }
            txtLatLong.text = latlong
        }
    }
}