package com.afoxplus.products.demo.global

import android.content.Intent
import com.afoxplus.module.delivery.flow.StartDemoFlow
import com.afoxplus.products.demo.delivery.views.activities.MainActivity
import com.afoxplus.uikit.activities.UIKitBaseActivity
import javax.inject.Inject

class ProductStartDemoFlow @Inject constructor() : StartDemoFlow {
    override fun start(activity: UIKitBaseActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

}