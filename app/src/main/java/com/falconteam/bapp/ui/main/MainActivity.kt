package com.falconteam.bapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.falconteam.bapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Setup NavController o fragmentos
    }
}