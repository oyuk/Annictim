package com.okysoft.annictim.presentation.widget

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ViewReviewValuesBinding
import com.okysoft.domain.model.Review

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
        review.ratingAnimationState?.let {
            setRatingValue(it, binding.animationTextView)
        }
        review.ratingMusicState?.let {
            setRatingValue(it, binding.musicTextView)
        }
        review.ratingStoryState?.let {
            setRatingValue(it, binding.storyTextView)
        }
        review.ratingCharacterState?.let {
            setRatingValue(it, binding.characterTextView)
        }
        review.ratingOverallState?.let {
            setRatingValue(it, binding.overallTextView)
        }
    }

    private fun setRatingValue(value: String, textView: TextView) {
        val c = ContextCompat.getDrawable(context, R.drawable.rating_value_view)
        val color: Int = when(value) {
            "bad" -> { R.color.red_300 }
            "average" -> { R.color.orange_300 }
            "good" -> { R.color.green_300 }
            "great" -> { R.color.blue_300 }
            else -> { R.color.orange_300 }
        }
        c?.setTint(ContextCompat.getColor(context, color));
        c?.setTintMode(PorterDuff.Mode.SRC_IN);
        textView.apply {
            this.text = value.capitalize()
            this.background = c
        }
    }

}