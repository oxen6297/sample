package com.example.sampletwo.fragments

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.text.SpannableStringBuilder
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
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

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, DataStoreViewModel>(R.layout.fragment_map),
    OnMapReadyCallback {

    override val viewModel: DataStoreViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var locationSources: FusedLocationSource
    private lateinit var marker: Marker
    private lateinit var naverMap: NaverMap
    private val locationManager by lazy {
        requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    override fun setUpBinding(view: View) {
        initMap()
        locationSources = FusedLocationSource(this, 1000)
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

    @Suppress("DEPRECATION")
    private fun getCoordinatesFromAddress(context: Context, address: String) {
        Geocoder(context).getFromLocationName(address, 1)?.forEach {
            setCamera(it.latitude, it.longitude)
            marker.apply {
                position = LatLng(it.latitude, it.longitude)
                captionText = address
                map = naverMap
            }
        }
    }

    @UiThread
    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
        marker = Marker()
        naverMap.apply {
            locationOverlay.isVisible = true
            locationSource = locationSources
            addOnLocationChangeListener { location ->
                locationTrackingMode = LocationTrackingMode.Face
                setCamera(location.latitude, location.longitude)
            }
        }
    }

    private fun setCamera(latitude: Double, longitude: Double) =
        naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))


    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    private fun checkPermission(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            context.showToast("위치 정보를 활성화 시켜주세요.")
        } else requestPermission()
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
}