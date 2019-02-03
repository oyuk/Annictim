package com.okysoft.annictim.presentation

//class StaffAdapter: RecyclerView.Adapter<BindingViewHolder<ItemStaffBinding>>() {
//
//    val items: BehaviorRelay<List<Staff>> = BehaviorRelay.createDefault(emptyList())
//    private val _onClick = MutableLiveData<Staff>()
//    val onClick: LiveData<Staff> = _onClick
//    private val bag = CompositeDisposable()
//
//    enum class ViewType(val num: Int)  {
//        ITEM(0), FOOTER(1)
//    }
//
//    private val TAG = StaffAdapter::class.java.name
//
//    init {
//        items.subscribeBy(
//            onNext = {notifyDataSetChanged()}
//        ).addTo(bag)
//    }
//
//    @Suppress("unused")
//    fun finalize() {
//        bag.dispose()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemStaffBinding> {
//        if (viewType == ViewType.ITEM.num) {
//            return BindingViewHolder(parent.context, parent, R.layout.item_staff)
//        }
//        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
//    }
//
//    override fun getItemCount(): Int {
//        if (items.value.isEmpty()) { return 1 }
//        return items.value.size
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        if (position == items.value.size) {
//            return ViewType.FOOTER.num
//        }
//        return ViewType.ITEM.num
//    }
//
//    override fun onBindViewHolder(holder: BindingViewHolder<ItemStaffBinding>, position: Int) {
//        val viewType = getItemViewType(position);
//        if (viewType == ViewType.FOOTER.num) {
//            return
//        }
//        val item = items.value[position]
//        holder.binding?.root?.setOnClickListener {
//            _onClick.postValue(item)
//        }
////        (holder.binding as ItemStaffBinding).apply {
////            staff = item
////            roleTextView.visibility = if (item.roleText.isBlank()) View.GONE else View.VISIBLE
////        }
//    }
//
//}

