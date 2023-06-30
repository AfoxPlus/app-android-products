package com.afoxplus.products.demo.global

import com.afoxplus.network.global.AppProperties
import javax.inject.Inject

class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun getCurrencyID(): String {
        return ""
    }

    override fun getDeviceData(): String {
        return ""
    }

    override fun getUserUUID(): String {
        TODO("Not yet implemented")
    }

    override fun isAppDebug(): Boolean {
        return true
    }
}