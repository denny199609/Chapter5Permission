package com.example.permissionactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.permissionactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnShowImage.setOnClickListener{
                loadImage()
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
}