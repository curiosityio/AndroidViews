package com.curiosityio.androidviewsexample.activity

import android.support.v4.app.Fragment
import com.curiosityio.andoidviews.activity.BaseFragmentActivity
import com.curiosityio.androidviewsexample.fragment.MainFragment

open class MainActivity : BaseFragmentActivity() {

    override fun getInitialFragment(): Fragment? = MainFragment.getInstance()

}