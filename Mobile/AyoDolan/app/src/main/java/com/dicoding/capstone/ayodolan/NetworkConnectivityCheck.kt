package com.dicoding.capstone.ayodolan

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

class NetworkConnectivityCheck(private val context: Context) : LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onActive() {
        super.onActive()
        updateConnection()
        when{
            Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N ->{
                connectivityManager.registerDefaultNetworkCallback(connectivtyCallback())
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ->{
                lollipopNetworkRequest()
            }
            else ->{
                @Suppress("DEPRECATION")
                context.registerReceiver(
                    networkReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            connectivityManager.unregisterNetworkCallback(connectivtyCallback())
        }else{
            context.unregisterReceiver(networkReceiver)
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest(){
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            connectivtyCallback()
        )
    }

     private fun connectivtyCallback(): ConnectivityManager.NetworkCallback{
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
             networkCallback = object : ConnectivityManager.NetworkCallback(){

                 override fun onLost(network: Network) {
                     super.onLost(network)
                     postValue(false)
                 }

                 override fun onAvailable(network: Network) {
                     super.onAvailable(network)
                     postValue(true)
                 }
             }
             return networkCallback
         }else{
             throw IllegalAccessError( "Error")
         }

     }

    private val networkReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }

    }
    @Suppress("DEPRECATION")
    private fun updateConnection(){
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }
}