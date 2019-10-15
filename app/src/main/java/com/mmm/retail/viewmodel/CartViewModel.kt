package com.mmm.retail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mmm.retail.helper.EventEnum
import com.mmm.retail.helper.RetailDatabase
import com.mmm.retail.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartViewModel(private val database:RetailDatabase) : BaseViewModel() {

    private val cartListMutableLiveData by lazy { MutableLiveData<List<Product>>() }
    val cartListLiveData: LiveData<List<Product>> by lazy { cartListMutableLiveData }
    private val cartDeleteMutableLiveData by lazy { MutableLiveData<EventEnum>() }
    val cartDeleteLiveData: LiveData<EventEnum> by lazy { cartDeleteMutableLiveData }

    fun getCartList() {
        disposable.add(database.cartAccess().fetchAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listProducts -> cartListMutableLiveData.postValue(listProducts) },
                        { it.printStackTrace() }))
    }

    fun deleteProduct(product: Product) {
        disposable.add(database.cartAccess().getSingleRecord(product.productId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    database.cartAccess().deleteRecord(product)
                    cartDeleteMutableLiveData.postValue(EventEnum.DELETE)
                }, { it.printStackTrace() }))
    }
}
