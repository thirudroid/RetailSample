package com.mmm.retail.helper

import android.content.Context

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mmm.retail.R
import com.mmm.retail.model.Product

import java.io.IOException
import java.io.InputStreamReader
import java.util.HashMap

class Utils {

    companion object {
        var ELECTRONIC = "Electronics"
        var FURNITURE = "Furniture"
        var ALL = "all"

        private val images: HashMap<String, Int>
            get() {
                val imagePair = HashMap<String, Int>()

                imagePair["001"] = R.drawable.microwave_oven
                imagePair["002"] = R.drawable.transparent
                imagePair["003"] = R.drawable.vacuum_cleaner
                imagePair["004"] = R.drawable.table
                imagePair["005"] = R.drawable.chair
                imagePair["006"] = R.drawable.almirah

                return imagePair
            }

        fun getProducts(context: Context): List<Product>? {
            return try {
                val gson = Gson()
                val reader = InputStreamReader(context.assets.open("products.json"))
                gson.fromJson<List<Product>>(reader, object : TypeToken<List<Product>>() {}.type)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }

        }

        fun getImage(productId: String): Int {
            return images[productId]!!
        }
    }

}
