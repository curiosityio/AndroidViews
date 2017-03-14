package com.curiosityio.loadingemptyviews.views

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.curiosityio.loadingemptyviews.R
import com.curiosityio.loadingemptyviews.widgets.EmptyView
import com.curiosityio.loadingemptyviews.widgets.LoadingView

open class LoadingEmptyLayout : LinearLayout {

    private lateinit var mContext: Context
    private lateinit var mAttrs: AttributeSet
    private var mDefStyleAttr: Int = 0

    private var mLoadingViewText: String = ""
    private var mEmptyViewDrawRes: Int = 0
    private var mEmptyViewMessage: String = ""

    private var mLightDarkMode: Int = 0

    private lateinit var mContentView: View
    private lateinit var mLoadingView: LoadingView
    private lateinit var mEmptyView: EmptyView

    private var mCurrentlyShownType: CurrentlyShownType? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    fun getCurrentlyShownType(): CurrentlyShownType {
        return mCurrentlyShownType!!
    }

    fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        mContext = context
        mAttrs = attrs
        mDefStyleAttr = defStyleAttr

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingEmptyLayout, 0, 0)

        try {
            mLoadingViewText = a.getString(R.styleable.LoadingEmptyLayout_loadingView_loadingText)
            mEmptyViewDrawRes = a.getResourceId(R.styleable.LoadingEmptyLayout_loadingView_emptyImageRes, -1)
            mEmptyViewMessage = a.getString(R.styleable.LoadingEmptyLayout_loadingView_emptyText)
            mLightDarkMode = a.getInt(R.styleable.LoadingEmptyLayout_loadingView_lightDarkMode, com.curiosityio.loadingemptyviews.widgets.LoadingView.LIGHT_MODE)
        } finally {
            a.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (childCount > 1) {
            throw RuntimeException(javaClass.simpleName + " cannot have more then 1 child view.")
        }

        if (childCount == 0) {
            throw RuntimeException("You forgot to add a child view to " + javaClass.simpleName)
        }

        mContentView = getChildAt(0)
        mLoadingView = LoadingView(mContext, mAttrs, mDefStyleAttr)
        mLoadingView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(mLoadingView)

        mEmptyView = EmptyView(mContext, mAttrs, mDefStyleAttr)
        mEmptyView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(mEmptyView)

        mLoadingView.setLightDarkMode(mLightDarkMode)
        mLoadingView.setLoadingText(mLoadingViewText)

        mEmptyView.setEmptyText(mEmptyViewMessage)
        mEmptyView.setEmptyImageView(mEmptyViewDrawRes)
        mEmptyView.setLightDarkMode(mLightDarkMode)

        showContentView(false)
    }

    fun setEmptyViewDrawRes(drawableRes: Int) {
        mEmptyView.setEmptyImageView(drawableRes)
    }

    fun setEmptyViewMessage(message: String) {
        mEmptyView.setEmptyText(message)
    }

    fun setLoadingViewText(message: String) {
        mLoadingView.setLoadingText(message)
    }

    fun showContentView(fade: Boolean) {
        if (mCurrentlyShownType == CurrentlyShownType.CONTENT) {
            return
        }

        mCurrentlyShownType = CurrentlyShownType.CONTENT

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(mLoadingView, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    mContentView.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    mLoadingView.visibility = View.GONE
                    mEmptyView.visibility = View.GONE
                    mContentView.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(mContentView, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            mContentView.visibility = View.VISIBLE
            mLoadingView.visibility = View.GONE
            mEmptyView.visibility = View.GONE
        }
    }

    fun showLoadingView(fade: Boolean) {
        if (mCurrentlyShownType === CurrentlyShownType.LOADING) {
            return
        }

        mCurrentlyShownType = CurrentlyShownType.LOADING

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(mContentView, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    mLoadingView.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    mContentView.visibility = View.GONE
                    mEmptyView.visibility = View.GONE
                    mLoadingView.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(mLoadingView, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            mContentView.visibility = View.GONE
            mEmptyView.visibility = View.GONE
            mLoadingView.visibility = View.VISIBLE
        }
    }

    fun showEmptyView(fade: Boolean) {
        if (mCurrentlyShownType === CurrentlyShownType.EMPTY) {
            return
        }

        mCurrentlyShownType = CurrentlyShownType.EMPTY

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(mContentView, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    mEmptyView.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    mContentView.visibility = View.GONE
                    mLoadingView.visibility = View.GONE
                    mEmptyView.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(mEmptyView, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            mContentView.visibility = View.GONE
            mLoadingView.visibility = View.GONE
            mEmptyView.visibility = View.VISIBLE
        }
    }

    enum class CurrentlyShownType {
        CONTENT,
        LOADING,
        EMPTY
    }

}