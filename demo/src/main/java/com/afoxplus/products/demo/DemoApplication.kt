package com.afoxplus.products.demo

import android.app.Application
import com.afoxplus.uikit.objects.vendor.Vendor
import com.afoxplus.uikit.objects.vendor.VendorShared
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DemoApplication : Application() {

    @Inject
    lateinit var vendorShared: VendorShared
    override fun onCreate() {
        super.onCreate()
        vendorShared.save(Vendor(tableId = "01", restaurantId = "648f94bd704db9741d1d2c04"))
    }
}