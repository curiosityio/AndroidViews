package com.levibostian.androidviewsexample.activity

import android.support.v4.app.Fragment
import com.levibostian.andoidviews.activity.BaseFragmentActivity
import com.levibostian.androidviewsexample.fragment.MainFragment

open class MainActivity : BaseFragmentActivity() {

    override fun getInitialFragment(): Fragment? = MainFragment.getInstance()

}