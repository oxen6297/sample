package com.example.sampletwo.fragments

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.text.SpannableStringBuilder
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentMapBinding
import com.example.sampletwo.extension.permissionPopUp
import com.example.sampletwo.extension.queryTextListener
import com.example.sampletwo.extension.showToast
import com.example.sampletwo.viewmodels.DataStoreViewModel
import com.google.firebase.annotations.concurrent.UiThread
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, DataStoreViewModel>(R.layout.fragment_map),
    OnMapReadyCallback {

    override val viewModel: DataStoreViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var marker: Marker
    private lateinit var naverMap: NaverMap

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    override fun setUpBinding(view: View) {
        if (!checkNetworkState(view.context)) view.context.showToast("네트워크 연결 상태를 확인해주세요.")
        initMap()
        initRequestLauncher(view.context)
        checkPermission(view.context)
        binding.searchView.apply {
            clearFocus()
            queryHint = SpannableStringBuilder("주소지를 입력해주세요.")
            isIconified = false
            setOnCloseListener {
                setQuery("", false)
                true
            }
            queryTextListener { query ->
                naverMap.locationTrackingMode = LocationTrackingMode.None
                getCoordinatesFromAddress(view.context, query)
            }
        }
    }

    @UiThread
    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
        marker = Marker()
        naverMap.apply {
            locationOverlay.isVisible = true
            locationSource = FusedLocationSource(this@MapFragment, 1000)
            addOnLocationChangeListener { location ->
                locationTrackingMode = LocationTrackingMode.Face
                setCamera(location.latitude, location.longitude)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun getCoordinatesFromAddress(context: Context, address: String) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Geocoder(context).getFromLocationName(address, 1) { addresses ->
                    addresses.forEach {
                        setCamera(it.latitude, it.longitude)
                        setMarker(it.latitude, it.longitude, address)
                    }
                }
            } else {
                Geocoder(context).getFromLocationName(address, 1)?.forEach {
                    setCamera(it.latitude, it.longitude)
                    setMarker(it.latitude, it.longitude, address)
                }
            }
        } catch (e: IOException) {
            context.showToast("네트워크 연결 상태를 확인해주세요.")
        }
    }

    private fun setMarker(latitude: Double, longitude: Double, address: String) {
        marker.apply {
            position = LatLng(latitude, longitude)
            captionText = address
            map = naverMap
        }
    }

    private fun setCamera(latitude: Double, longitude: Double) =
        CoroutineScope(Dispatchers.Main).launch {
            naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
        }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    private fun checkPermission(context: Context) {
        val locationManager =
            requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) requestPermission()
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            context.showToast("위치 정보를 활성화 시켜주세요.")
        }
    }

    private fun initRequestLauncher(context: Context) {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if (it.all { permission -> !permission.value }) context.permissionPopUp()
            }
    }

    private fun requestPermission() {
        val permissionList = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        requestPermissionLauncher.launch(permissionList)
    }

    private fun checkNetworkState(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(ConnectivityManager::class.java)
        val network: Network = connectivityManager.activeNetwork ?: return false
        val actNetwork: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }

}