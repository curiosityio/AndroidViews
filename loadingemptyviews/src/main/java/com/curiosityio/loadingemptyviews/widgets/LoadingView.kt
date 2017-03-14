package com.curiosityio.loadingemptyviews.widgets

import android.widget.LinearLayout
import android.widget.TextView
import android.annotation.TargetApi
import android.content.Context
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION_CODES
import android.os.Build
import android.view.LayoutInflater
import android.view.Gravity
import android.content.res.TypedArray
import com.curiosityio.loadingemptyviews.widgets.LoadingView
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.curiosityio.loadingemptyviews.R

open class LoadingView : LinearLayout {

    companion object {
        val LIGHT_MODE = 0
        val DARK_MODE = 1
    }

    private lateinit var mLoadingTextView: TextView
    private lateinit var mContext: Context

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        mContext = context

        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)

        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setPadding(10, 0, 10, 0)

        mLoadingTextView = findViewById(R.id.loading_textview) as TextView

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0)

        try {
            setLoadingText(a.getString(R.styleable.LoadingView_loading_loadingText))
            setLightDarkMode(a.getInt(R.styleable.LoadingView_loading_lightDarkMode, LIGHT_MODE))
        } finally {
            a.recycle()
        }
    }

    fun setLightDarkMode(mode: Int) {
        if (mode == LIGHT_MODE) {
            mLoadingTextView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black))
        } else if (mode == DARK_MODE) {
            mLoadingTextView.setTextColor(ContextCompat.getColor(mContext, android.R.color.white))
        }
    }

    fun setLoadingText(loadingText: String?) {
        if (loadingText != null) {
            mLoadingTextView.text = loadingText
        }
    }

}