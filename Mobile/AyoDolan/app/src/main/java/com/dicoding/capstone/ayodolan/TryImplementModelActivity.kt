package com.dicoding.capstone.ayodolan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.capstone.ayodolan.databinding.ActivityTryImplementModelBinding

class TryImplementModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTryImplementModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTryImplementModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var text = binding.edText.text.toString()

        binding.btnGo.setOnClickListener {

        }


    }
}