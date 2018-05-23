package com.levibostian.loadingemptyviews.extensions

import android.content.Context
import android.os.Build

fun Context.getColorSupport(id: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColor(id)
    } else {
        resources.getColor(id)
    }
}