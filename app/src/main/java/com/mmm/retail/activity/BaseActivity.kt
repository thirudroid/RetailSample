package com.mmm.retail.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mmm.retail.R
import com.mmm.retail.helper.BaseToolbar

abstract class BaseActivity : AppCompatActivity() {
    protected var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        configureToolbar()
    }

    protected abstract fun setLayout(): Int

    private fun configureToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar?.let{
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            if (it is BaseToolbar)
                it.updateCartCount()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onDestroy() {
        toolbar?.let{
            (toolbar as BaseToolbar).onDestroyToolbar()
        }
        super.onDestroy()
    }
}
