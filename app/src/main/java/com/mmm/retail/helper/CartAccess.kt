package com.mmm.retail.helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mmm.retail.model.Product
import io.reactivex.Single

@Dao
interface CartAccess {

    @Insert
    fun insertOnlySingleRecord(product: Product)

    @Query("SELECT * FROM cart")
    fun fetchAllData(): Single<List<Product>>

    @Query("SELECT * FROM cart WHERE id =:productID")
    fun getSingleRecord(productID: String): Single<Product>

    @Query("SELECT COUNT(*) from cart")
    fun countCart(): Single<Int>

    @Query("UPDATE cart SET quantity=:quantity WHERE id=:productId")
    fun updateRecord(productId: String, quantity: Int)

    @Delete
    fun deleteRecord(product: Product)
}
