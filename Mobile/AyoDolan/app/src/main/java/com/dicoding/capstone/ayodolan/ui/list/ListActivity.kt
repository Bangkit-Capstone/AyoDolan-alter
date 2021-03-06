package com.dicoding.capstone.ayodolan.ui.list

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.ayodolan.core.data.source.loopj.MainViewModel
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.ui.VacationAdapter
import com.dicoding.capstone.ayodolan.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    private lateinit var message : String
    private lateinit var mainViewModel: MainViewModel
    companion object{
        const val EXTRA_ACTIVITY = "extra_activty"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stateLoading(true)
        val connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @Suppress("DEPRECATION") val networkInfo = connectionManager.activeNetworkInfo
        @Suppress("DEPRECATION")
        if (networkInfo != null && networkInfo.isConnected){
            stateNotConnection(false)
        }else{
            stateNotConnection(true)
            stateLoading(false)
            binding.btnRetry.setOnClickListener {
                recreate()
            }
        }

        val pantai = resources.getString(R.string.pantai)
        val gunung = resources.getString(R.string.gunung)
        val taman = resources.getString(R.string.taman)
        val cagar = resources.getString(R.string.cagar_budaya)

        mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        val vacationAdapter = VacationAdapter()
        val extras = intent.extras
        if (extras != null){
            val type = extras.getString(EXTRA_ACTIVITY)
            if (type != null){
                when(type){
                    pantai ->{
                        message = pantai
                            mainViewModel.getPantai().observe(this,{
                                stateLoading(false)
                                vacationAdapter.setVacation(it)
                            })
                    }
                    gunung ->{
                        message = gunung
                            mainViewModel.getGunung().observe(this,{
                                stateLoading(false)
                                vacationAdapter.setVacation(it)
                            })
                    }
                    taman -> {
                        message = taman
                        mainViewModel.getTaman().observe(this,{
                            stateLoading(false)
                            vacationAdapter.setVacation(it)
                        })

                    }
                    cagar -> {
                        message = cagar
                        mainViewModel.getCagar().observe(this,{
                            stateLoading(false)
                            vacationAdapter.setVacation(it)
                        })

                    }
                }
                mainViewModel.getError().observe(this,{
                    if (it != null) Toast.makeText(this, it,Toast.LENGTH_LONG).show()
                })
                supportActionBar?.title = message
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            with(binding){
                itemsList.layoutManager = LinearLayoutManager(this@ListActivity)
                itemsList.setHasFixedSize(true)
                itemsList.adapter = vacationAdapter
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

    private fun stateNotConnection(state: Boolean){
        if (state){
            binding.layoutNoConnect.visibility = View.VISIBLE
            binding.itemsList.visibility = View.GONE
        }else{
            binding.layoutNoConnect.visibility = View.GONE
            binding.itemsList.visibility = View.VISIBLE
        }
    }

}