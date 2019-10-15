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
import kotlinx.android.synthetic.main.cart_list_item_view.view.*

class CartListAdapter(private val context: Context, private val removeListener: OnProductRemoveListener) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private var productList = listOf<Product>()

    fun addData(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product:Product){
            Picasso.with(context)
                    .load(Utils.getImage(product.productId))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(itemView.imgCartPD)
            itemView.txtCartPDName.text = product.name
            itemView.txtCartPDPrice.text = product.price.toString()
            itemView.txtCartPDQuantity.text = context.getString(R.string.card_product_count, product.quantity)
            itemView.setOnClickListener {
                ProductDetailsActivity.start(context,product,true)
            }

            itemView.btnCartPDRemove.setOnClickListener { removeListener.onRemove(product) }
        }
    }

    interface OnProductRemoveListener {
        fun onRemove(product: Product)
    }
}
