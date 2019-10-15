package com.mmm.retail

import com.mmm.retail.helper.RetailDatabase
import com.mmm.retail.viewmodel.CartViewModel
import com.mmm.retail.viewmodel.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val retailModules = module {
    single { RetailDatabase.getInstant(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { ProductViewModel(get()) }
}
