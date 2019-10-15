package com.mmm.retail.viewmodel

import android.content.Context

import com.mmm.retail.RetailApplication
import com.mmm.retail.helper.EventEnum
import com.mmm.retail.helper.RetailDatabase
import com.mmm.retail.helper.Utils
import com.mmm.retail.model.Product

import java.util.ArrayList

import io.reactivex.schedulers.Schedulers

class ProductViewModel(private val database: RetailDatabase) : BaseViewModel() {

    fun addProductToCart(product: Product, updateProduct:()->Unit,insetProduct:()->Unit) {
        disposable.add(database.cartAccess().getSingleRecord(product.productId)
                .subscribeOn(Schedulers.io())
                .subscribe({ prod ->
                    database.cartAccess().updateRecord(product.productId, product.quantity + prod.quantity)
                    updateProduct()
                    RetailApplication.app().event().send(EventEnum.UPDATE)
                }, {
                    database.cartAccess().insertOnlySingleRecord(product)
                    insetProduct()
                    RetailApplication.app().event().send(EventEnum.INSERT)
                }))
    }

    fun updateProductToCart(productId: String, quantity: Int,updateProduct:()->Unit) {
        disposable.add(database.cartAccess().getSingleRecord(productId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    database.cartAccess().updateRecord(productId, quantity)
                    RetailApplication.app().event().send(EventEnum.UPDATE)
                    updateProduct()
                }, {
                    it.printStackTrace()
                }))
    }

    fun getProducts(applicationContext:Context,key: String): List<Product>? {
        val list = Utils.getProducts(applicationContext)
        return if (key.equals(Utils.ALL, ignoreCase = true)) {
             list
        } else {
            val products = ArrayList<Product>()
            for (product in list!!) {
                if (product.category.equals(key, ignoreCase = true)) {
                    products.add(product)
                }
            }
             products
        }
    }
}
