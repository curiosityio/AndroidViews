package com.curiosityio.andoidviews.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.curiosityio.andoidviews.R

abstract class BaseToolbarActivity() : BaseActivity() {

    protected lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    // Override to provide your own layout ID.
    override fun getLayoutId(): Int = R.layout.activity_base_toolbar

    // Override to provide your own fragment ID.
    override fun getFragmentContainerId(): Int = R.id.activity_base_toolbar_fragment_container

    // Override to provide your own Toolbar.
    open fun getToolbarId(): Int = R.id.toolbar

    // Override to provide own color of Toolbar.
    open fun getToolbarBackground(): Int = R.attr.colorPrimary

    private fun setupViews() {
        toolbar = findViewById(getToolbarId()) as Toolbar
        toolbar.setBackgroundColor(getToolbarBackground())

        setSupportActionBar(toolbar)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    // Call from child class to make back button a X looking button instead of back button.
    protected fun useCloseButton() {
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_white)
    }

    // Call from child class to add back button to toolbar.
    protected fun setBackButtonOnToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setToobarTitle(title: String) {
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
