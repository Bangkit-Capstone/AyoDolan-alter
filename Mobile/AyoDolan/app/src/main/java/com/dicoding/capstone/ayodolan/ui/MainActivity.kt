package com.dicoding.capstone.ayodolan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.databinding.ActivityMainBinding
import com.dicoding.capstone.ayodolan.ui.list.ListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        

        binding.pantai.setOnClickListener {
            val moveActivity = Intent(this,ListActivity::class.java)
            moveActivity.putExtra(ListActivity.EXTRA_ACTIVITY, resources.getString(R.string.pantai))
            startActivity(moveActivity)
        }
        binding.gunung.setOnClickListener {
            val moveActivty = Intent(this,ListActivity::class.java)
            moveActivty.putExtra(ListActivity.EXTRA_ACTIVITY,resources.getString(R.string.gunung))
            startActivity(moveActivty)
        }
        binding.cagar.setOnClickListener {
            val moveActivity = Intent(this,ListActivity::class.java)
            moveActivity.putExtra(ListActivity.EXTRA_ACTIVITY,resources.getString(R.string.cagar_budaya))
            startActivity(moveActivity)
        }
        binding.taman.setOnClickListener {
            val moveActivity = Intent(this,ListActivity::class.java)
            moveActivity.putExtra(ListActivity.EXTRA_ACTIVITY,resources.getString(R.string.taman))
            startActivity(moveActivity)
        }

    }
}