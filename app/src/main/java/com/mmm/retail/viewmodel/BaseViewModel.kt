package com.mmm.retail.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    val disposable:CompositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}