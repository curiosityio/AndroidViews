package com.levibostian.loadingemptyviews.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import android.annotation.TargetApi
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION_CODES
import android.os.Build
import android.view.LayoutInflater
import android.view.Gravity
import android.content.res.TypedArray
import com.levibostian.loadingemptyviews.widgets.EmptyView
import android.widget.ImageView
import com.levibostian.loadingemptyviews.R
import com.levibostian.loadingemptyviews.extensions.getColorSupport
import com.levibostian.loadingemptyviews.views.LoadingEmptyLayout

class EmptyView: LinearLayout {

    var emptyImageView: ImageView? = null
        set(value) {
            field = value
            // In case these variables were set while the EmptyView was null, reset them to run their code to set the properties in the EmptyView.
            this.emptyImageRes = this.emptyImageRes
        }
    var emptyTextView: TextView? = null
        set(value) {
            field = value
            // In case these variables were set while the EmptyView was null, reset them to run their code to set the properties in the EmptyView.
            this.emptyText = this.emptyText
        }

    var emptyText: String? = null
        set(value) {
            field = value
            value?.let { emptyTextView?.text = it }
        }
    var emptyImageRes: Int = 0
        set(value) {
            field = value
            if (value >= 0) {
                emptyImageView?.setImageResource(value)
                emptyImageView?.visibility = VISIBLE
            }
        }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    private fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_empty, this, true)

        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setPadding(10, 0, 10, 0)

        emptyImageView = findViewById(R.id.empty_view_imageview)
        emptyTextView = findViewById(R.id.empty_view_textview)

        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyleAttr, 0)
        try {
            emptyImageRes = a.getResourceId(R.styleable.EmptyView_empty_emptyImageRes, -1)
            emptyText = a.getString(R.styleable.EmptyView_empty_emptyText)
        } finally {
            a.recycle()
        }
    }

}