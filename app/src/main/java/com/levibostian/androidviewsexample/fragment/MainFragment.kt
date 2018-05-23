package com.levibostian.androidviewsexample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.levibostian.androidviewsexample.R
import com.levibostian.copycatsnackbar.SnackbarCopyCatView
import kotlinx.android.synthetic.main.fragment_main.view.*

open class MainFragment : Fragment() {

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        view.show_snackbar_copycat.setOnClickListener { view.main_fragment_copy_cat_snackbar.show() }
        view.hide_snackbar_copycat.setOnClickListener { view.main_fragment_copy_cat_snackbar.dismiss() }

        return view
    }

}