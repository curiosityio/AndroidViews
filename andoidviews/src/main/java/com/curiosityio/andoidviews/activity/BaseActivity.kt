package com.curiosityio.andoidviews.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.curiosityio.andoidviews.R

abstract class BaseActivity : AppCompatActivity() {

    var backButtonPressedAlready = false

    interface BackPressedListener {
        fun backPressed(): Boolean
    }
    protected var backPressedListener: BackPressedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        if (savedInstanceState == null) {
            addFragment(getInitialFragment(), getFragmentContainerId())
        }
    }

    open fun getLayoutId(): Int {
        return R.layout.activity_base
    }

    open fun getFragmentContainerId(): Int {
        return R.id.fragment_container
    }

    override fun setContentView(layoutResID: Int) {
        if (layoutResID != getLayoutId()) {
            throw RuntimeException("You cannot call setContentView() directly. Must override getLayoutId() and BaseActivity will call it for you.")
        }

        super.setContentView(layoutResID)
    }

    abstract fun getInitialFragment(): Fragment?

    override fun onBackPressed() {
        if (!backButtonPressedAlready) {
            backButtonPressedAlready = true

            if (backPressedListener != null) {
                if (!backPressedListener!!.backPressed()) {
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        supportFragmentManager.popBackStack()
                    } else {
                        super.onBackPressed()
                    }
                }
            } else {
                super.onBackPressed()
            }

            backButtonPressedAlready = false
        }
    }

    protected fun replaceFragment(fragment: Fragment?, fragmentContainer: Int, addToBackstack: Boolean = false) {
        setFragmentInContainer(fragment, fragmentContainer, true, addToBackstack)
    }

    protected fun addFragment(fragment: Fragment?, fragmentContainer: Int) {
        setFragmentInContainer(fragment, fragmentContainer, false)
    }

    private fun setFragmentInContainer(fragment: Fragment?, fragmentContainer: Int, replace: Boolean = true, addToBackstack: Boolean = false) {
        fragment?.let { fragment ->
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            if (replace) fragmentTransaction.replace(fragmentContainer, fragment) else fragmentTransaction.add(fragmentContainer, fragment)
            if (addToBackstack) fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()
        }
    }

    // view transition animations with lollipop and above.
    // `startActivityWithTransition(DestActivity.getIntent(), srcViewToTransition, R.string.view_transition_name)`
    protected fun startActivityWithTransition(intent: Intent, transitionView: View, transitionName: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, transitionView, getString(transitionName))
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    protected fun startActivityWithTransition(intent: Intent, vararg transitions: Pair<View, String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *transitions)
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

}

fun BaseActivity.isOrientationPortrait(): Boolean {
    return resources.configuration.orientation === Configuration.ORIENTATION_PORTRAIT
}