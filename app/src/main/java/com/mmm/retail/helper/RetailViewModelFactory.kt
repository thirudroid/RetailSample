package com.mmm.retail.helper

import androidx.lifecycle.ViewModelProvider
import com.mmm.retail.viewmodel.CartViewModel
import com.mmm.retail.viewmodel.ProductViewModel

class RetailViewModelFactory(private val database: RetailDatabase) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CartViewModel::class.java) -> CartViewModel(database) as T
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> ProductViewModel(database) as T
            else -> modelClass as T
        }
    }
}