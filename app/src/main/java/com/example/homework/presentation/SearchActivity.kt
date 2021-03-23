package com.example.homework.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.api.ApiFactory
import com.example.homework.data.dto.CityDTO
import com.example.homework.data.dto.CityAdapter
import com.example.homework.domain.helpers.PermissionHelper
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(),CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private val api = ApiFactory.weatherAPI

    private lateinit var permissionHelper:PermissionHelper

    private lateinit var searchView: SearchView
    private lateinit var searchManager: SearchManager

    private lateinit var constraintLayoutSearchActivity: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var city: String
    private lateinit var listDTO:ArrayList<CityDTO>

    private var cordLatitude:Double? = null
    private var cordLongitude:Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        constraintLayoutSearchActivity = findViewById(R.id.constraintLayout_activity_search)

        permissionHelper = PermissionHelper()

        recyclerView = findViewById(R.id.cities_list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            itemAnimator = DefaultItemAnimator()
        }
        getWeatherListAfterGrantedLocation()
    }

    override fun onStart() {
        super.onStart()
        permissionHelper.check(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)

        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName)
        )
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {

            @SuppressLint("ShowToast")
            override fun onQueryTextSubmit(query: String?): Boolean {
                city = searchView.query.toString()
                lifecycleScope.launch {
                    try {
                        api.getWeatherByName(city).id.let{
                            goToWeatherInfo(it)
                        }
                    } catch (e: HttpException) {
                        Snackbar.make(constraintLayoutSearchActivity,
                                "This city does not exist",
                                Snackbar.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        }


        )
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(!permissionHelper.handle(requestCode, permissions, grantResults)){
            permissionHelper.check(this)
            Snackbar.make(constraintLayoutSearchActivity,
                    "no permissions dude",
                    Snackbar.LENGTH_SHORT).show()
        }
        else{
            Snackbar.make(constraintLayoutSearchActivity,
                    "nice dude",
                    Snackbar.LENGTH_SHORT).show()
            getWeatherListAfterGrantedLocation()
        }
    }

    private fun goToWeatherInfo(id:Int){
        val sendWeatherIntent = Intent(this, WeatherInfoActivity::class.java)
        sendWeatherIntent.putExtra("id",id)
        startActivity(sendWeatherIntent)
    }

    private fun getWeatherListAfterGrantedLocation(){
        listDTO = ArrayList<CityDTO>()
        val mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
                cordLatitude = location.latitude
                cordLongitude = location.longitude
                Log.d("Coords", "latitude: ${location.latitude}, longitude:${location.longitude}")
                cordLatitude?.let { lt ->
                    cordLongitude?.let { ln ->
                        launch {
                            val str =
                                    ApiFactory.getWeatherList(lt, ln)
                            listDTO = ArrayList()
                            str.forEach { weather ->
                                listDTO.add(
                                        CityDTO(
                                                weather.id,
                                                weather.name,
                                                weather.main.temp.toInt()
                                        )
                                )
                            }
                            listDTO.forEach({})
                            runOnUiThread {
                                recyclerView.adapter = CityAdapter(listDTO) {
                                    goToWeatherInfo(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}