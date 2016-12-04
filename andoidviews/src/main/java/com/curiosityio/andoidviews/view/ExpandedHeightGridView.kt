package com.curiosityio.andoidviews.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.GridView

/*
<com.curiosityio.androidviews.view.ExpandedHeightGridView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:numColumns="2"
            android:verticalSpacing="10dp"
            android:horizontalSpacing="10dp"
            android:stretchMode="columnWidth"
            android:gravity="center" />
 */

// Credits: http://stackoverflow.com/a/8483078/1486374
open class ExpandedHeightGridView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : GridView(context, attrs, defStyle) {

    private var expanded = true

    fun isExpanded(): Boolean {
        return expanded
    }

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // HACK! TAKE THAT ANDROID!
        if (isExpanded()) {
            // Calculate entire height by providing a very large height hint.
            // View.MEASURED_SIZE_MASK represents the largest height possible.
            val expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, expandSpec)

            val params = layoutParams
            params.height = measuredHeight
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

}