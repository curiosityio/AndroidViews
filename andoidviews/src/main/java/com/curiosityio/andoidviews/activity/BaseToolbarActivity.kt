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

    override fun getLayoutId(): Int {
        return R.layout.activity_base_toolbar
    }

    override fun getFragmentContainerId(): Int {
        return R.id.activity_base_toolbar_fragment_container
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    // Call from child class to make back button a X looking button instead of back button.
    protected fun useCloseButton() {
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_white_24px)
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
