package com.mmm.retail

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mmm.retail.helper.Utils
import com.mmm.retail.model.Product
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    @Test
    @Throws(Exception::class)
    fun testLocalImageItem(){
        Assert.assertThat(Utils.getImage("003"), Matchers.equalTo(R.drawable.vacuum_cleaner))
    }
    @Test
    @Throws(Exception::class)
    fun testJsonToProductList(){
        Assert.assertThat(Utils.getProducts(context)?.size,Matchers.equalTo(6))
        Assert.assertThat(Utils.getProducts(context)?.get(2)?.productId,Matchers.equalTo("003"))
    }
}