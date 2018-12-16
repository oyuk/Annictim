package com.okysoft.annictim

import android.content.Context
import android.util.AttributeSet

class SquareImageView(context: Context, attrs: AttributeSet) : android.support.v7.widget.AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}
