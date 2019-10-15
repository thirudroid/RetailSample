package com.mmm.retail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmm.retail.R
import com.mmm.retail.activity.ProductDetailsActivity
import com.mmm.retail.helper.Utils
import com.mmm.retail.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_item_view.view.*

class ProductListAdapter(private val context: Context, private val productList: List<Product>?) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList?.get(position))
    }

    override fun getItemCount(): Int {
        return productList?.size?:0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product?){
            product?.let { Picasso.with(context)
                    .load(Utils.getImage(it.productId))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(itemView.imgPlProduct)
                itemView.txtPlName.text = it.name
                itemView.txtPlPrice.text = it.price.toString()
                itemView.setOnClickListener {_->
                    ProductDetailsActivity.start(context,it,false)
                } }
        }
    }
}
