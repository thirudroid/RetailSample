package com.mmm.retail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mmm.retail.R
import com.mmm.retail.helper.Utils
import com.mmm.retail.model.Product
import com.mmm.retail.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailsActivity : BaseActivity() {

    override fun setLayout() = R.layout.activity_product_details

    private val product: Product by lazy { intent.getParcelableExtra("PRODUCT") as Product }
    private val isFromCart: Boolean by lazy { intent.getBooleanExtra("FROM_CART", false) }
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Picasso.with(this)
                .load(Utils.getImage(product.productId))
                .placeholder(R.mipmap.ic_launcher)
                .into(imagePD)

        txtNamePD.text = product.name
        txtPricePD.text = product.price.toString()
        if (product.quantity > 0) {
            txtQuantityPD.text = product.quantity.toString()
        }

        if (isFromCart) {
            btnAddToCart.text = getString(R.string.save)
        }

        btnAddToCart.setOnClickListener{addToCard(getQuantityCount())}
        btnAdd.setOnClickListener {addProductQuantity(getQuantityCount())}
        btnSub.setOnClickListener {subProductQuantity(getQuantityCount())}
    }
    private fun getQuantityCount():Int{
        val count = txtQuantityPD.text.toString()
       return  if(count.isNotEmpty()) count.toInt() else 0
    }

    private fun addToCard(count:Int) {
            if (isFromCart) {
                viewModel.updateProductToCart(product.productId, count){
                    finish()
                }
            } else {
                product.quantity = count
                viewModel.addProductToCart(product,{
                    finish()
                },{
                    finish()
                })
            }
    }

    private fun addProductQuantity(count:Int) {
        if (count != 99) {
            txtQuantityPD.text = (count + 1).toString()
        }
    }

    private fun subProductQuantity(count:Int) {
        if (count != 1) {
            txtQuantityPD.text = (count - 1).toString()
        }
    }

    companion object{
        fun start(context: Context,product:Product,isFromCart:Boolean){
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT", product)
            intent.putExtra("FROM_CART", isFromCart)
            context.startActivity(intent)
        }
    }
}
