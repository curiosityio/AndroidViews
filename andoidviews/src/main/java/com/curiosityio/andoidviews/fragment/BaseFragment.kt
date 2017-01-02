package com.curiosityio.andoidviews.fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.curiosityio.andoidviews.activity.BaseActivity

abstract class BaseFragment() : Fragment() {

    protected var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        baseActivity = activity as? BaseActivity
    }

}
