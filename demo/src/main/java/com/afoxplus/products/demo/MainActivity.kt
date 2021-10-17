package com.afoxplus.products.demo

import androidx.viewpager2.widget.ViewPager2
import com.afoxplus.products.delivery.flow.ProductFlow
import com.afoxplus.products.demo.databinding.ActivityMainBinding
import com.afoxplus.uikit.activities.BaseActivity
import com.afoxplus.uikit.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var productFlow: ProductFlow
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun setMainView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setUpView() {
        viewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            listOf(productFlow.getFragmentRecommendedProducts())
        )
        binding.viewPagerMarket.adapter = viewPagerAdapter
        binding.viewPagerMarket.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}