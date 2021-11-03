package com.afoxplus.products.demo.delivery.views.activities

import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.afoxplus.products.delivery.flow.ProductFlow
import com.afoxplus.products.demo.databinding.ActivityMainBinding
import com.afoxplus.products.demo.delivery.viewmodels.MainViewModel
import com.afoxplus.uikit.activities.BaseActivity
import com.afoxplus.uikit.adapters.ViewPagerAdapter
import com.afoxplus.uikit.bus.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var productFlow: ProductFlow
    private val viewModel: MainViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun setMainView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setUpView() {
        viewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            listOf(productFlow.getProductMenuFragment(), productFlow.getProductsSaleFragment())
        )
        binding.viewPagerMarket.adapter = viewPagerAdapter
        binding.viewPagerMarket.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    override fun observerViewModel() {
        viewModel.productClicked.observe(this, EventObserver { product ->
            Toast.makeText(this, "${product.name} is Clicked", Toast.LENGTH_SHORT).show()
        })

        viewModel.productOfferClicked.observe(this, EventObserver { product ->
            Toast.makeText(this, "${product.name} offer is Clicked", Toast.LENGTH_SHORT).show()
        })
    }
}