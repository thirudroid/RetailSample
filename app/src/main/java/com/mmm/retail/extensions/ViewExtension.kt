package com.mmm.retail.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mmm.retail.viewmodel.BaseViewModel

fun View.visible(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}

fun <T : BaseViewModel> AppCompatActivity.obtainViewModel(viewModel:Class<T>,factory:ViewModelProvider.Factory?=null):T{
    return if(factory == null){
        ViewModelProviders.of(this).get(viewModel)
    }else{
        ViewModelProvider(this,factory).get(viewModel)
    }
}

fun <T : BaseViewModel> Fragment.obtainViewModel(viewModel:Class<T>,factory:ViewModelProvider.Factory?=null):T{
    return if(factory == null){
        ViewModelProviders.of(this).get(viewModel)
    }else{
        ViewModelProvider(this,factory).get(viewModel)
    }
}

fun AppCompatActivity.replaceFragment(containerID: Int, fragment: Fragment, canBack: Boolean) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(containerID, fragment, fragment.javaClass.getSimpleName())
    if (canBack) {
        fragmentTransaction.addToBackStack(null)
    }
    fragmentTransaction.commit()
}
