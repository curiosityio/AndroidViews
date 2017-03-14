package com.curiosityio.andoidviews.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.curiosityio.andoidviews.R
import kotlinx.android.synthetic.main.activity_base_toolbar.*

abstract class BaseToolbarActivity : BaseFragmentActivity() {

    protected lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    // Override to provide your own layout ID.
    override fun getLayoutId(): Int = R.layout.activity_base_toolbar

    // Override to provide your own fragment container ID.
    override fun getFragmentContainerId(): Int = R.id.activity_base_toolbar_fragment_container

    // We ask for sub class to provide toolbar so it's styled to the app theme, not the library theme.
    abstract fun getToolbarLayout(): Int

    // If you do not like the shadow below the Toolbar, override this.
    open fun showShadowBelowToolbar(): Boolean = true

    // Override to decide if you want the embedded fragment under the toolbar or below it. Good for when you want to use a transparent toolbar.
    open fun setFragmentBelowToolbar(): Boolean = true

    private fun setupViews() {
        toolbar_viewstub.layoutResource = getToolbarLayout()
        val inflatedView: View = toolbar_viewstub.inflate()

        if (inflatedView !is Toolbar) {
            throw RuntimeException("Your getToolbarLayout() does not return a Toolbar.")
        } else {
            toolbar = inflatedView
        }

        setSupportActionBar(toolbar)

        if (!showShadowBelowToolbar()) toolbar_bottom_shadow.visibility = View.GONE

        if (setFragmentBelowToolbar()) {
            if (toolbar.id == View.NO_ID) {
                throw RuntimeException("Your toolbar must set an id if you want to use setFragmentBelowToolbar()")
            }

            val params = findViewById(getFragmentContainerId()).layoutParams as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.BELOW, toolbar.id)
        }
    }

    // Call from child class to make back button a X looking button instead of back button.
    protected fun useCloseButton() {
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_white)
    }

    // Call from child class to add back button to toolbar.
    protected fun setBackButtonOnToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // If your toolbar has it's own way to set the title, override this function.
    open fun setToobarTitle(title: String) {
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
