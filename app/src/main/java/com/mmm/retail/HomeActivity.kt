package com.mmm.retail

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem

import com.mmm.retail.activity.BaseActivity
import com.mmm.retail.extensions.replaceFragment
import com.mmm.retail.fragment.ProductsFragment
import com.mmm.retail.helper.Utils
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var currentMenuItem: Int = 0

    override fun setLayout() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        currentMenuItem = R.id.nav_home
        replaceFragment(R.id.home_container, ProductsFragment.getInstant(Utils.ALL), false)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (currentMenuItem != id) {
            currentMenuItem = id
            selectMenu(id)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun selectMenu(id: Int) {
        when (id) {
            R.id.nav_home -> replaceFragment(R.id.home_container, ProductsFragment.getInstant(Utils.ALL), false)
            R.id.nav_electronics -> replaceFragment(R.id.home_container, ProductsFragment.getInstant(Utils.ELECTRONIC), false)
            R.id.nav_furniture -> replaceFragment(R.id.home_container, ProductsFragment.getInstant(Utils.FURNITURE), false)
        }
    }
}
