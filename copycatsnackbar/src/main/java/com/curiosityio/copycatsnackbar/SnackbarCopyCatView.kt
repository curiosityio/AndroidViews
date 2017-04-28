package com.curiosityio.copycatsnackbar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.content.res.TypedArray

// I created this becauase in my app, I would show a snackbar over top of a recyclerview. The recyclerview was redrawn which made the snackbar disappear even though I needed it there infinite time. This snackbar is for when you need more control over a snackbar. This exists in your layout file and will not go away when the view you attach to goes away like a traditional snackbar.
open class SnackbarCopyCatView : FrameLayout {

    private lateinit var mContext: Context

    private lateinit var mTextView: TextView

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        mContext = context

        LayoutInflater.from(context).inflate(R.layout.view_snackbar_copy_cat, this, true)

        mTextView = findViewById(R.id.copy_cat_snackbar_textview) as TextView

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.SnackbarCopyCatView, 0, 0)
        try {
            a.getString(R.styleable.SnackbarCopyCatView_snackbar_copy_cat_text)?.let {
                setText(it)
            }
        } finally {
            a.recycle()
        }

        visibility = View.INVISIBLE // so the view does not show up by default.
    }

    fun setText(message: String?) {
        mTextView.text = message
    }
    
    fun show(duration: Long = 400) {
        visibility = View.VISIBLE
        if (this.translationY == height.toFloat()) {
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(this, "translationY", height.toFloat(), 0.toFloat())
            )
            animatorSet.duration = duration
            animatorSet.start()
        }
    }

    fun dismiss(duration: Long = 400) {
        visibility = View.VISIBLE
        if (this.translationY != height.toFloat()) {
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(this, "translationY", 0.toFloat(), height.toFloat())
            )
            animatorSet.duration = duration
            animatorSet.start()
        }
    }

}