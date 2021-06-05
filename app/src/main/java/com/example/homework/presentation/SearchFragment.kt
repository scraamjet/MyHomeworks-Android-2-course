package com.example.homework.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.api.entity.WeatherResponse
import com.example.homework.data.repository.RemoteRepository
import com.example.homework.domain.dto.CityAdapter
import com.example.homework.domain.dto.CityItem
import com.example.homework.domain.facade.CacheProviderFacade
import com.example.homework.domain.helpers.ConnectionHelper
import com.example.homework.domain.helpers.PermissionHelper
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext


class SearchFragment(val onClick: (Int)-> Unit) : Fragment(),CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private lateinit var permissionHelper: PermissionHelper
    private lateinit var connectionHelper: ConnectionHelper
    private lateinit var cacheProviderFacade: CacheProviderFacade
    private lateinit var repository: RemoteRepository

    private lateinit var searchView: SearchView

    private lateinit var recyclerView: RecyclerView
    private lateinit var city: String
    private lateinit var itemList: ArrayList<CityItem>

    private var cordLatitude: Double? = null
    private var cordLongitude: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        permissionHelper = PermissionHelper()
        connectionHelper = ConnectionHelper()
        repository = RemoteRepository()
        searchView = view?.findViewById(R.id.sv_fragment_search)

        context?.let {
            cacheProviderFacade = CacheProviderFacade(it)
        }

        activity?.let {
            recyclerView = view?.findViewById(R.id.cities_list)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                itemAnimator = DefaultItemAnimator()
            }
            getWeatherListAfterGrantedLocation()
            permissionHelper.check(it)
        }


        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


            @SuppressLint("ShowToast")
            override fun onQueryTextSubmit(query: String?): Boolean {
                city = searchView.query.toString()
                context?.let { context ->
                    if (connectionHelper.hasConnect(context)) {
                        lifecycleScope.launch {
                            try {
                                repository.getWeatherByName(city).id.let {
                                    onClick(it)
                                }
                            } catch (e: HttpException) {
                                val im =
                                    activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                                im.hideSoftInputFromWindow(view.windowToken, 0)
                                Snackbar.make(
                                    view,
                                    "This city does not exist",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        val im =
                            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        im.hideSoftInputFromWindow(view.windowToken, 0)
                        Snackbar.make(
                            view,
                            "There is no internet connection",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        }


        )
    }


    private fun getWeatherListAfterGrantedLocation() {
        itemList = ArrayList<CityItem>()
        activity?.let { activity ->
            context?.let { context ->
                if (connectionHelper.hasConnect(context)) {
                    val mFusedLocationClient =
                        LocationServices.getFusedLocationProviderClient(activity)

                    if (ActivityCompat.checkSelfPermission(
                            activity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(
                            activity,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        mFusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
                            cordLatitude = location.latitude
                            cordLongitude = location.longitude
                            Log.d(
                                "Coords",
                                "latitude: ${location.latitude}, longitude:${location.longitude}"
                            )
                            cordLatitude?.let { lt ->
                                cordLongitude?.let { ln ->
                                    launch {
                                        val str =
                                            repository.getWeatherList(lt, ln)
                                        itemList = ArrayList()
                                        str.forEach { weather ->
                                            itemList.add(
                                                CityItem(
                                                    weather.id,
                                                    weather.name,
                                                    weather.main.temp
                                                )
                                            )
                                        }
                                        cacheProviderFacade.updateDB(str as ArrayList<WeatherResponse>)
                                        itemList.forEach({})
                                        activity?.runOnUiThread() {
                                            recyclerView.adapter = CityAdapter(itemList) {
                                                onClick(it)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{

                    }
                } else {
                    launch {
                        itemList = cacheProviderFacade.getItemFromDB() as ArrayList<CityItem>
                        itemList.forEach() {
                            activity?.runOnUiThread() {
                                recyclerView.adapter = CityAdapter(itemList) {
                                    onClick(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}