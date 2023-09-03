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
import com.afoxplus.uikit.result.UIKitResultState
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

        mProductsSale.postValue(ListLoading())
        when (val result = fetchProductsByCurrentRestaurant("")) {
            is UIKitResultState.Success -> {
                val data = result.data.map { item ->
                    ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_SALE, item)
                }
                if (data.isEmpty())
                    getProductsStringsHelper.getSalesEmptyStringsUIModel().let { resource ->
                        mProductsSale.postValue(resource.toEmptyProduct())
                    }
                else
                    mProductsSale.postValue(ListSuccess(data))
            }

            is UIKitResultState.Error -> {
                mProductsSale.postValue(ListError(Exception(result.message)))
            }

        }
    }

    fun fetchProductOffers() = viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
        when (val result = fetchSaleOfferByCurrentRestaurant()) {
            is UIKitResultState.Success -> {
                val data = result.data.map { item ->
                    ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_OFFER, item)
                }
                if (data.isEmpty())
                    mProductOffer.postValue(ListEmptyData())
                else
                    mProductOffer.postValue(ListSuccess(data))
            }

            is UIKitResultState.Error -> {
                mProductOffer.postValue(ListError(Exception(result.message)))
            }
        }
    }

    fun fetchProductsAppetizer() =
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            mProductAppetizer.postValue(ListLoading())
            when (val result = fetchAppetizerByCurrentRestaurant()) {
                is UIKitResultState.Success -> {
                    if (result.data.isEmpty())
                        mProductAppetizer.postValue(ListEmptyData())
                    else {
                        val mapResult = result.data.map { item ->
                            ProductUIModel(
                                ProductUIModel.VIEW_TYPE_PRODUCT_APPETIZER,
                                item
                            )
                        }
                        mProductAppetizer.postValue(ListSuccess(mapResult))
                    }
                }

                is UIKitResultState.Error -> {
                    mProductAppetizer.postValue(ListError(Exception(result.message)))
                }

                else -> {
                    // Nothing
                }
            }


        }

    fun fetchProductsMenu() = viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
        mProductsMenu.postValue(ListLoading())
        when (val result = fetchMenuByCurrentRestaurant()) {
            is UIKitResultState.Success -> {
                if (result.data.isEmpty()) {
                    getProductsStringsHelper.getMenuEmptyStringsUIModel().let { resource ->
                        mProductsMenu.postValue(resource.toEmptyProduct())
                    }
                } else {
                    val mapResult = result.data.map { item ->
                        ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_MENU, item)
                    }
                    mProductsMenu.postValue(ListSuccess(mapResult))
                }
            }

            is UIKitResultState.Error -> {
                mProductsMenu.postValue(ListError(Exception(result.message)))
            }
        }
    }

    fun fetchProductsHomeOffer() =
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            mProductsHomeOffer.postValue(ListLoading())
            when (val result = fetchHomeOffer()) {
                is UIKitResultState.Success -> {
                    if (result.data.isEmpty())
                        mProductsHomeOffer.postValue(ListEmptyData())
                    else {
                        val mapResult = result.data.map { item ->
                            ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_HOME_OFFER, item)
                        }
                        mProductsHomeOffer.postValue(ListSuccess(mapResult))
                    }
                }

                is UIKitResultState.Error -> {
                    mProductsHomeOffer.postValue(ListError(Exception(result.message)))
                }
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