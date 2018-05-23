package com.levibostian.loadingemptyviews.widgets

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
import com.levibostian.loadingemptyviews.widgets.LoadingView
import android.util.AttributeSet
import com.levibostian.loadingemptyviews.R
import com.levibostian.loadingemptyviews.extensions.getColorSupport
import com.levibostian.loadingemptyviews.views.LoadingEmptyLayout

open class LoadingView : LinearLayout {

    var loadingTextView: TextView? = null
        set(value) {
            field = value
            // In case these variables were set while the EmptyView was null, reset them to run their code to set the properties in the EmptyView.
            this.loadingText = this.loadingText
        }

    var loadingText: String? = ""
        set(value) {
            field = value
            value?.let { loadingTextView?.text = it }
        }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    private fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)

        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setPadding(10, 0, 10, 0)

        loadingTextView = findViewById(R.id.loading_textview)

        val a = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, defStyleAttr, 0)
        try {
            loadingText = a.getString(R.styleable.LoadingView_loading_loadingText)
        } finally {
            a.recycle()
        }
    }

}