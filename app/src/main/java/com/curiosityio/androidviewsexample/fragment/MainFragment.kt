package com.curiosityio.androidviewsexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curiosityio.andoidviews.fragment.BaseFragment
import com.curiosityio.androidviewsexample.R

open class MainFragment : BaseFragment() {

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        return view
    }

}