package com.curiosityio.androidviewsexample.activity

import android.support.v4.app.Fragment
import com.curiosityio.andoidviews.activity.BaseActivity
import com.curiosityio.androidviewsexample.fragment.MainFragment

open class MainActivity : BaseActivity() {

    override fun getInitialFragment(): Fragment? {
        return MainFragment.getInstance()
    }

}