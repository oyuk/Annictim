package com.okysoft.annictim.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.R
import com.okysoft.annictim.api.model.response.Review
import com.okysoft.annictim.databinding.ItemReviewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ReviewsAdapter: RecyclerView.Adapter<BindingViewHolder<ItemReviewBinding>>() {

    val items: BehaviorRelay<List<Review>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Review>()
    val onClick: LiveData<Review> = _onClick
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = ReviewsAdapter::class.java.name

    init {
        items.subscribeBy(
                onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemReviewBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_review)
        }
        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
    }

    override fun getItemCount(): Int {
        if (items.value.isEmpty()) { return 1 }
        return items.value.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == items.value.size) {
            return ViewType.FOOTER.num
        }
        return ViewType.ITEM.num
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemReviewBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value[position]
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }



//        holder.binding?.chart?.setDrawBarShadow(false)
//        val description = Description()
//        description.text = ""
//        holder.binding?.chart?.description = description
//        holder.binding?.chart?.legend?.setEnabled(false)
//        holder.binding?.chart?.setPinchZoom(false)
//        holder.binding?.chart?.setDrawValueAboveBar(false)
//        holder.binding?.chart?.isDoubleTapToZoomEnabled = false
//        holder.binding?.chart?.legend?.setEnabled(false)
//        holder.binding?.chart?.setPinchZoom(false)
//        holder.binding?.chart?.setDrawValueAboveBar(false)
//
//        //Display the axis on the left (contains the labels 1*, 2* and so on)
//        val xAxis = holder.binding?.chart!!.getXAxis()
//        xAxis.setDrawGridLines(false)
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
//        xAxis.setEnabled(true)
//        xAxis.setDrawAxisLine(false)
//
//
//        val yLeft = holder.binding?.chart?.axisLeft
//
////Set the minimum and maximum bar lengths as per the values that they represent
//        yLeft.axisMaximum = 4f
//        yLeft.axisMinimum = 0f
//        yLeft.isEnabled = false
//
//        //Set label count to 5 as we are displaying 5 star rating
////        xAxis.setLabelCount(5)
//
////Now add the labels to be added on the vertical axis
//        val values = arrayOf("映像 ", "音楽 ", "ストーリー ", "キャラクター ", "全体 ")
//        xAxis.valueFormatter = XAxisValueFormatter(values)
//
//        val yRight = holder.binding?.chart?.axisRight
//        yRight.setDrawAxisLine(true)
//        yRight.setDrawGridLines(false)
//        yRight.isEnabled = false
//
//        //Set bar entries and add necessary formatting
//        val entries = ArrayList<BarEntry>()
//        entries.add(BarEntry(0f, item.ratingNum(Review.Rating.animation)))
//        entries.add(BarEntry(1f, item.ratingNum(Review.Rating.mutic)))
//        entries.add(BarEntry(2f, item.ratingNum(Review.Rating.story)))
//        entries.add(BarEntry(3f, item.ratingNum(Review.Rating.character)))
//        entries.add(BarEntry(4f, item.ratingNum(Review.Rating.overall)))
//
//        val barDataSet = BarDataSet(entries, "Bar Data Set")
//
//        barDataSet.setColors(
//                ContextCompat.getColor(holder.binding?.chart!!.context, R.color.red_500),
//                ContextCompat.getColor(holder.binding?.chart!!.context, R.color.green_500),
//                ContextCompat.getColor(holder.binding?.chart!!.context, R.color.yellow_500),
//                ContextCompat.getColor(holder.binding?.chart!!.context, R.color.green_700),
//                ContextCompat.getColor(holder.binding?.chart!!.context, R.color.yellow_700))
//
//        //Add animation to the graph
//        holder.binding?.chart?.animateY(2000)
//
//        holder.binding?.chart?.setDrawBarShadow(true)
//        barDataSet.barShadowColor = Color.argb(40, 150, 150, 150)
//        val data = BarData(barDataSet)
//
//        //Set the bar width
//        //Note : To increase the spacing between the bars set the value of barWidth to < 1f
//        data.barWidth = 0.5f
//
//        //Finally set the data and refresh the graph
//        holder.binding?.chart?.data = data
//        holder.binding?.chart?.invalidate()

        holder.binding?.run {
            review = item
            comment.visibility = if(item.body.isEmpty()) View.GONE else View.VISIBLE
            if (item.hasRating) {
                reviewValueView.setReviewValues(item)
                reviewValueView.visibility = View.VISIBLE
            } else {
                reviewValueView.visibility = View.GONE
            }

        }
    }

}

//class XAxisValueFormatter(private val values: Array<String>) : IAxisValueFormatter {
//
//    override fun getFormattedValue(value: Float, axis: AxisBase): String {
//        // "value" represents the position of the label on the axis (x or y)
//        return this.values[value.toInt()]
//    }
//
//}
