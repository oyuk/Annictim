//package com.okysoft.annictim.presentation.works
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.okysoft.annictim.databinding.ItemWorkBinding
//import com.okysoft.annictim.extension.setImage
//import com.okysoft.infra.extension.seasonText
//import com.okysoft.infra.fragment.WorkFeed
//
//class WorksAdapter: ListAdapter<WorkFeed, WorksAdapter.WorkFeedViewHolder>(WorkFeedDiffCallback()) {
//
//    class WorkFeedViewHolder(val binding: ItemWorkBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(workFeed: WorkFeed) {
//            binding.work = workFeed
//            binding.seasonName.text = workFeed.seasonText()
//            val imageUrl = workFeed.image?.recommendedImageUrl
//            if (imageUrl != null) {
//                binding.imageView.setImage(imageUrl)
//            } else {
//                binding.imageView.setImageDrawable(null)
//            }
//        }
//    }
//
//    class WorkFeedDiffCallback: DiffUtil.ItemCallback<WorkFeed>() {
//        override fun areItemsTheSame(oldItem: WorkFeed, newItem: WorkFeed): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: WorkFeed, newItem: WorkFeed): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val _onClick = MutableLiveData<WorkFeed>()
//    val onClick: LiveData<WorkFeed> = _onClick
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkFeedViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return WorkFeedViewHolder(ItemWorkBinding.inflate(inflater, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: WorkFeedViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//        holder.binding.root.setOnClickListener {
//            _onClick.postValue(item)
//        }
//    }
//
//}
//
