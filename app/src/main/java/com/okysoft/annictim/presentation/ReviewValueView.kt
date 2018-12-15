package com.okysoft.annictim.presentation

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.okysoft.annictim.api.model.response.Review
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ViewReviewValuesBinding

class ReviewValueView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewReviewValuesBinding by lazy {
        val inflater = LayoutInflater.from(context)
        return@lazy DataBindingUtil.inflate<ViewReviewValuesBinding>(inflater, R.layout.view_review_values, this, true)
    }

    fun setReviewValues(review: Review) {
        setRatingValue(review.ratingAnimationState, binding.animationTextView)
        setRatingValue(review.ratingMusicState, binding.musicTextView)
        setRatingValue(review.ratingStoryState, binding.storyTextView)
        setRatingValue(review.ratingCharacterState, binding.characterTextView)
        setRatingValue(review.ratingOverallState, binding.overallTextView)
    }

    private fun setRatingValue(value: String, textView: TextView) {
        val c = ContextCompat.getDrawable(context, R.drawable.rating_value_view)
        val color: Int = when(value) {
            "bad" -> { R.color.red_500 }
            "average" -> { R.color.orange_500 }
            "good" -> { R.color.green_500 }
            "great" -> { R.color.blue_500 }
            else -> { R.color.orange_500 }
        }
        c?.setTint(ContextCompat.getColor(context, color));
        c?.setTintMode(PorterDuff.Mode.SRC_IN);
        textView.apply {
            this.text = value
            this.background = c
        }
    }

}