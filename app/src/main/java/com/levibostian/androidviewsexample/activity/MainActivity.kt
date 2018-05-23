package com.levibostian.androidviewsexample.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.levibostian.androidviewsexample.R
import com.levibostian.androidviewsexample.fragment.LoadingEmptyViewFragment
import com.levibostian.loadingemptyviews.extensions.getColorSupport
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupView()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, LoadingEmptyViewFragment.getInstance())
                    .commit()
        }
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer_layout.closeDrawers()

            var fragment: Fragment = LoadingEmptyViewFragment.getInstance()
            when (menuItem.itemId) {
                R.id.loading_empty_view -> {
                    fragment = LoadingEmptyViewFragment.getInstance()
                }
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}