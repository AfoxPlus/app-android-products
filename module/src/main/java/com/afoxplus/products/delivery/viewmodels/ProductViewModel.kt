package com.afoxplus.products.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.helpres.GetProductsStringsHelper
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.*
import com.afoxplus.uikit.bus.EventBusListener
import com.afoxplus.uikit.di.UIKitIODispatcher
import com.afoxplus.uikit.di.UIKitMainDispatcher
import com.afoxplus.uikit.views.status.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductViewModel @Inject constructor(
    private val fetchProducts: FetchProduct,
    private val fetchHomeOffer: FetchHomeOffer,
    private val fetchSaleOffer: FetchSaleOffer,
    private val fetchAppetizer: FetchAppetizer,
    private val fetchMenu: FetchMenu,
    private val productEventBus: EventBusListener,
    private val getProductsStringsHelper: GetProductsStringsHelper,
    @UIKitMainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @UIKitIODispatcher private val ioDispatcher: CoroutineDispatcher
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

    fun fetchProductSales() = viewModelScope.launch(ioDispatcher) {
        try {
            mProductsSale.postValue(ListLoading())
            val result = fetchProducts("").map { item ->
                ProductUIModel(ProductUIModel.VIEW_TYPE_PRODUCT_SALE, item)
            }
            if (result.isEmpty()) {
                val emptyLbsResult = getProductsStringsHelper.getSalesEmptyStringsUIModel()
                mProductsSale.postValue(
                    EmptyProduct(
                        emptyLbsResult.lblTitle,
                        emptyLbsResult.lblDescription
                    )
                )
            } else
                mProductsSale.postValue(ListSuccess(result))
        } catch (ex: Exception) {
            mProductsSale.postValue(ListError(ex))
        }
    }

    fun fetchProductOffers() = viewModelScope.launch(ioDispatcher) {
        try {
            val data = fetchSaleOffer().map { item ->
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

    fun fetchProductsAppetizer() = viewModelScope.launch(ioDispatcher) {
        try {
            mProductAppetizer.postValue(ListLoading())
            val result = fetchAppetizer()
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

    fun fetchProductsMenu() = viewModelScope.launch(ioDispatcher) {
        try {
            mProductsMenu.postValue(ListLoading())
            val result = fetchMenu()
            if (result.isEmpty()) {
                val emptyLbsResult = getProductsStringsHelper.getMenuEmptyStringsUIModel()
                mProductsMenu.postValue(
                    EmptyProduct(
                        emptyLbsResult.lblTitle,
                        emptyLbsResult.lblDescription
                    )
                )
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

    fun fetchProductsHomeOffer() = viewModelScope.launch(ioDispatcher) {
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

    fun onClickProductEvent(product: Product) = viewModelScope.launch(mainDispatcher) {
        productEventBus.send(OnClickProductSaleEvent.build(product))
    }

    fun onClickProductOfferEvent(product: Product) = viewModelScope.launch(mainDispatcher) {
        productEventBus.send(OnClickProductOfferEvent.build(product))
    }

    data class EmptyProduct<E>(val title: String, val description: String) : ListState<E>
}