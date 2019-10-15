package com.mmm.retail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.retail.R
import com.mmm.retail.RetailApplication
import com.mmm.retail.adapter.CartListAdapter
import com.mmm.retail.helper.EventEnum
import com.mmm.retail.model.Product
import com.mmm.retail.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity() {
    override fun setLayout() = R.layout.activity_cart

    private val adapter: CartListAdapter by lazy { CartListAdapter(this,object : CartListAdapter.OnProductRemoveListener{
        override fun onRemove(product: Product) {
            viewModel.deleteProduct(product)
        }

    }) }
    private val viewModel: CartViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartListView.setHasFixedSize(true)
        cartListView.layoutManager = LinearLayoutManager(this)
        cartListView.adapter = adapter

        viewModel.cartDeleteLiveData.observe(this, Observer {
            viewModel.getCartList()
            RetailApplication.app().event().send(EventEnum.DELETE)
        })

        viewModel.cartListLiveData.observe(this, Observer {
            adapter.addData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartList()
    }

    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, CartActivity::class.java))
        }
    }
}
