package com.curiosityio.andoidviews.fragment

import android.app.Fragment
import android.content.Context
import com.curiosityio.andoidviews.activity.BaseActivity

abstract class BaseFragment() : Fragment() {

    protected var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        baseActivity = activity as? BaseActivity
    }

}
