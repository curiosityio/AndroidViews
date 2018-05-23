package com.levibostian.loadingemptyviews.views

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.levibostian.loadingemptyviews.R
import com.levibostian.loadingemptyviews.widgets.EmptyView
import com.levibostian.loadingemptyviews.widgets.LoadingView

open class LoadingEmptyLayout : LinearLayout {

    private lateinit var attrs: AttributeSet

    private var loadingViewText: String? = null
        set(value) {
            field = value
            value?.let { loadingView?.loadingText = it }
        }
    private var emptyViewDrawRes: Int = 0
        set(value) {
            field = value
            emptyView?.emptyImageRes = value
        }

    private var emptyViewMessage: String? = null
        set(value) {
            field = value
            value?.let { emptyView?.emptyText = it }
        }

    private var contentView: View? = null
    private var loadingView: LoadingView? = null
        set(value) {
            field = value
            // In case these variables were set while the EmptyView was null, reset them to run their code to set the properties in the EmptyView.
            this.loadingViewText = this.loadingViewText
        }

    private var emptyView: EmptyView? = null
        set(value) {
            field = value
            // In case these variables were set while the EmptyView was null, reset them to run their code to set the properties in the EmptyView.
            this.emptyViewDrawRes = this.emptyViewDrawRes
            this.emptyViewMessage = this.emptyViewMessage
        }

    var currentlyShownType: CurrentlyShownType? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    private fun initialize(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        this.attrs = attrs

        val a = context.obtainStyledAttributes(attrs, R.styleable.LoadingEmptyLayout, 0, 0)
        try {
            loadingViewText = a.getString(R.styleable.LoadingEmptyLayout_loadingEmpty_loadingText)
            emptyViewDrawRes = a.getResourceId(R.styleable.LoadingEmptyLayout_loadingEmpty_emptyImageRes, -1)
            emptyViewMessage = a.getString(R.styleable.LoadingEmptyLayout_loadingEmpty_emptyText)
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

        contentView = getChildAt(0)

        val loadingView = LoadingView(context, attrs, 0)
        loadingView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(loadingView)
        this.loadingView = loadingView

        val emptyView = EmptyView(context, attrs, 0)
        emptyView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(emptyView)
        this.emptyView = emptyView

        showContentView(false)
    }

    fun showContentView(fade: Boolean) {
        if (currentlyShownType == CurrentlyShownType.CONTENT) return
        currentlyShownType = CurrentlyShownType.CONTENT

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(loadingView!!, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    contentView!!.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    loadingView!!.visibility = View.GONE
                    emptyView!!.visibility = View.GONE
                    contentView!!.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(contentView!!, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            contentView!!.visibility = View.VISIBLE
            loadingView!!.visibility = View.GONE
            emptyView!!.visibility = View.GONE
        }
    }

    fun showLoadingView(fade: Boolean) {
        if (currentlyShownType === CurrentlyShownType.LOADING) return
        currentlyShownType = CurrentlyShownType.LOADING

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(contentView!!, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    loadingView!!.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    contentView!!.visibility = View.GONE
                    emptyView!!.visibility = View.GONE
                    loadingView!!.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(loadingView!!, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            contentView!!.visibility = View.GONE
            emptyView!!.visibility = View.GONE
            loadingView!!.visibility = View.VISIBLE
        }
    }

    fun showEmptyView(fade: Boolean) {
        if (currentlyShownType === CurrentlyShownType.EMPTY) return
        currentlyShownType = CurrentlyShownType.EMPTY

        if (fade) {
            val fadeOut = ObjectAnimator.ofFloat(contentView!!, "alpha", 1f, 0f).setDuration(200)
            fadeOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    emptyView!!.visibility = View.GONE
                }
                override fun onAnimationEnd(animation: Animator) {
                    contentView!!.visibility = View.GONE
                    loadingView!!.visibility = View.GONE
                    emptyView!!.visibility = View.VISIBLE

                    ObjectAnimator.ofFloat(emptyView!!, "alpha", 0f, 1f).setDuration(200).start()
                }
                override fun onAnimationCancel(animation: Animator) {
                }
                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            fadeOut.start()
        } else {
            contentView!!.visibility = View.GONE
            loadingView!!.visibility = View.GONE
            emptyView!!.visibility = View.VISIBLE
        }
    }

    enum class CurrentlyShownType {
        CONTENT,
        LOADING,
        EMPTY
    }

}