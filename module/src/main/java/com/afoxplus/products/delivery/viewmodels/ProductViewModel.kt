package com.afoxplus.products.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.extensions.toEmptyProduct
import com.afoxplus.products.delivery.helpres.GetProductsStringsHelper
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.*
import com.afoxplus.uikit.bus.UIKitEventBusWrapper
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.uikit.views.status.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductViewModel @Inject constructor(
    private val fetchProductsByCurrentRestaurant: FetchProductByCurrentRestaurant,
    private val fetchHomeOffer: FetchHomeOffer,
    private val fetchSaleOfferByCurrentRestaurant: FetchSaleOfferByCurrentRestaurant,
    private val fetchAppetizerByCurrentRestaurant: FetchAppetizerByCurrentRestaurant,
    private val fetchMenuByCurrentRestaurant: FetchMenuByCurrentRestaurant,
    private val eventBusWrapper: UIKitEventBusWrapper,
    private val getProductsStringsHelper: GetProductsStringsHelper,
    private val uiKitCoroutineDispatcher: UIKitCoroutineDispatcher,
) : ViewModel() {

    private val mProductsSale: MutableLiveData<ListState<ProductUIModel>> by lazy { MutableLiveData<ListState<ProductUIModel>>() }
    private val mProductOffer: MutableLiveData<ListState<ProductUIModel>> by lazy { MutableLiveData<ListState<ProductUIModel>>() }
    private val mProductAppetizer: MutableLiveData<ListState<ProductUIModel>> by lazy { MutableLiveData<ListState<ProductUIModel>>() }
    private val mProductsMenu: MutableLiveData<ListState<ProductUIModel>> by lazy { MutableLiveData<ListState<ProductUIModel>>() }
    private val mProductsHomeOffer: MutableLiveData<ListState<ProductUIModel>> by lazy { MutableLiveData<ListState<ProductUIModel>>() }

    val productsSale: LiveData<ListState<ProductUIModel>> get() = mProductsSale
    val productOffer: LiveData<ListState<ProductUIModel>> get() = mProductOffer
    val productAppetizer: LiveData<ListState<ProductUIModel>> get() = mProductAppetizer
    val productMenu: LiveData<ListState<ProductUIModel>> get() = mProductsMenu
    val productsHomeOffer: LiveData<ListState<ProductUIModel>> get() = mProductsHomeOffer

    fun fetchProductSales() = viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
        try {
            mProductsSale.postValue(ListLoading())
            val result = fetchProductsByCurrentRestaurant("").map { item ->
                ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_SALE, item)
            }
            if (result.isEmpty())
                getProductsStringsHelper.getSalesEmptyStringsUIModel().let { resource ->
                    mProductsSale.postValue(resource.toEmptyProduct())
                }
            else
                mProductsSale.postValue(ListSuccess(result))
        } catch (ex: Exception) {
            mProductsSale.postValue(ListError(ex))
        }
    }

    fun fetchProductOffers() = viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
        try {
            val data = fetchSaleOfferByCurrentRestaurant().map { item ->
                ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_OFFER, item)
            }
            if (data.isEmpty())
                mProductOffer.postValue(ListEmptyData())
            else
                mProductOffer.postValue(ListSuccess(data))
        } catch (ex: Exception) {
            mProductOffer.postValue(ListError(ex))
        }
    }

    fun fetchProductsAppetizer() =
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            try {
                mProductAppetizer.postValue(ListLoading())
                val result = fetchAppetizerByCurrentRestaurant()
                if (result.isEmpty())
                    mProductAppetizer.postValue(ListEmptyData())
                else {
                    val mapResult = result.map { item ->
                        ProductUIModel(
                            ProductUIModel.VIEW_TYPE_PRODUCT_APPETIZER,
                            item
                        )
                    }
                    mProductAppetizer.postValue(ListSuccess(mapResult))
                }
            } catch (ex: Exception) {
                mProductAppetizer.postValue(ListError(ex))
            }
        }

    fun fetchProductsMenu() = viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
        try {
            mProductsMenu.postValue(ListLoading())
            val result = fetchMenuByCurrentRestaurant()
            if (result.isEmpty()) {
                getProductsStringsHelper.getMenuEmptyStringsUIModel().let { resource ->
                    mProductsMenu.postValue(resource.toEmptyProduct())
                }
            } else {
                val mapResult = result.map { item ->
                    ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_MENU, item)
                }
                mProductsMenu.postValue(ListSuccess(mapResult))
            }
        } catch (ex: Exception) {
            mProductsMenu.postValue(ListError(ex))
        }
    }

    fun fetchProductsHomeOffer() =
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            try {
                mProductsHomeOffer.postValue(ListLoading())
                val result = fetchHomeOffer()
                if (result.isEmpty())
                    mProductsHomeOffer.postValue(ListEmptyData())
                else {
                    val mapResult = result.map { item ->
                        ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_HOME_OFFER, item)
                    }
                    mProductsHomeOffer.postValue(ListSuccess(mapResult))
                }
            } catch (ex: Exception) {
                mProductsHomeOffer.postValue(ListError(ex))
            }
        }

    fun onClickProductEvent(product: Product) =
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            eventBusWrapper.send(OnClickProductSaleEvent(product))
        }

    fun onClickProductOfferEvent(product: Product) =
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            eventBusWrapper.send(OnClickProductOfferEvent(product))
        }

    data class EmptyProduct<T>(val title: Int, val description: Int) : ListState<T>
}