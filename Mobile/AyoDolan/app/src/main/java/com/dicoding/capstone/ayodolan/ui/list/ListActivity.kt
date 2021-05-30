package com.dicoding.capstone.ayodolan.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.ViewModelFactory
import com.dicoding.capstone.ayodolan.core.data.ui.VacationAdapter
import com.dicoding.capstone.ayodolan.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    private lateinit var message : String
    companion object{
        const val EXTRA_ACTIVITY = "extra_activty"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stateLoading(true)

        val pantai = resources.getString(R.string.pantai)
        val gunung = resources.getString(R.string.gunung)
        val taman = resources.getString(R.string.taman)
        val cagar = resources.getString(R.string.cagar_budaya)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[ListViewModel::class.java]
        val pantaiAdapter = VacationAdapter()
        val extras = intent.extras
        if (extras != null){
            val type = extras.getString(EXTRA_ACTIVITY)
            if (type != null){
                when(type){
                    pantai ->{
                        message = pantai
                        viewModel.getMoviePopuler().observe(this,{
                            stateLoading(false)
                            if (it.isEmpty()) Toast.makeText(this,"Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                            else pantaiAdapter.setVacation(it)
                        })
                    }
                    gunung ->{
                        message = gunung
                        viewModel.getMovieRate().observe(this,{
                            pantaiAdapter.setVacation(it)
                        })
                    }
                    taman -> message = taman
                    cagar -> message = cagar
                }
                supportActionBar?.title = message
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding){
            itemsList.layoutManager = LinearLayoutManager(this@ListActivity)
            itemsList.setHasFixedSize(true)
            itemsList.adapter = pantaiAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun stateLoading(state: Boolean){
        if (state)binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}