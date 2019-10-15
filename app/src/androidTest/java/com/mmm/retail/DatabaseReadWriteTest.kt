package com.mmm.retail

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mmm.retail.helper.CartAccess
import com.mmm.retail.helper.RetailDatabase
import com.mmm.retail.model.Product
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseReadWriteTest {
    private lateinit var cartDao: CartAccess
    private lateinit var db: RetailDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, RetailDatabase::class.java).build()
        cartDao = db.cartAccess()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeCartAndRead() {
        val product: Product = Product(productId = "100").also {
            it.name = "Mobile"
            it.category = "Electronics"
            it.image = "mobile.png"
            it.price = 10000
            it.quantity = 2
        }
        cartDao.insertOnlySingleRecord(product)
        val byName = cartDao.getSingleRecord("100")
        assertThat(byName.blockingGet(), equalTo(product))
    }

    @Test
    @Throws(Exception::class)
    fun getAllCartData(){
        Assert.assertEquals(cartDao.fetchAllData().blockingGet().size,0)
    }
}