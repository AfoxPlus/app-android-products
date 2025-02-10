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
        vendorShared.save(Vendor(tableId = "01", restaurantId = "671885a91cfe6b6e8339ea70"))
    }
}