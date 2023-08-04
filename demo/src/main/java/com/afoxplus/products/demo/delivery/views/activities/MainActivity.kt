package com.afoxplus.products.demo.delivery.views.activities

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.afoxplus.products.delivery.flow.ProductFlow
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.demo.databinding.ActivityMainBinding
import com.afoxplus.products.demo.delivery.viewmodels.MainViewModel
import com.afoxplus.uikit.activities.UIKitBaseActivity
import com.afoxplus.uikit.adapters.UIKitViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : UIKitBaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var productFlow: ProductFlow
    private val viewModel: MainViewModel by viewModels()
    private lateinit var viewPagerAdapter: UIKitViewPagerAdapter

    override fun setMainView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setUpView() {
        viewPagerAdapter = UIKitViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            listOf(
                productFlow.getProductMenuFragment(),
                productFlow.getProductsSaleFragment(),
                productFlow.getProductHomeOfferFragment()
            )
        )
        binding.viewPagerMarket.adapter = viewPagerAdapter
        binding.viewPagerMarket.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    override fun observerViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.onEventBusListener.collectLatest { events ->
                when (events) {
                    is OnClickProductOfferEvent -> {
                        Toast.makeText(
                            this@MainActivity,
                            "${events.product.name} offer is Clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is OnClickProductSaleEvent -> {
                        Toast.makeText(
                            this@MainActivity,
                            "${events.product.name} is Clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}