package com.learningAndroiddeve.androidcalenderview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningAndroiddeve.androidcalenderview.data.ChaletsProperties
import com.learningAndroiddeve.androidcalenderview.databinding.ChaletViewItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class ChaletAdapter(private val onClickListener: OnClickListener) : ListAdapter<ChaletsProperties, ChaletAdapter.ChaletPropertyViewHolder>(DiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    var unfilteredList: List<ChaletsProperties>? = null

    fun addList(allChaletReservationsResponse: List<ChaletsProperties>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main){
                unfilteredList = allChaletReservationsResponse
                submitList(allChaletReservationsResponse)
            }
        }
    }


    class ChaletPropertyViewHolder(private var binding: ChaletViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chaletsProperties : ChaletsProperties,clickListener: OnClickListener) {
            binding.chalet = chaletsProperties
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaletPropertyViewHolder {
        return ChaletPropertyViewHolder(ChaletViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ChaletPropertyViewHolder, position: Int) {
        val chaletsProperty = getItem(position)
        holder.bind(chaletsProperty,onClickListener)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ChaletsProperties>() {
        override fun areItemsTheSame(oldItem: ChaletsProperties, newItem: ChaletsProperties): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ChaletsProperties, newItem: ChaletsProperties): Boolean {
            return oldItem.chaletName == newItem.chaletName
        }
    }

    fun filter(query:String) {
                val list = mutableListOf<ChaletsProperties>()
                if(query.isNotEmpty()) {
                    unfilteredList?.filter {
                        it.chaletNormalDayPrice.toString().toLowerCase(Locale.getDefault()).contains(query) ||
                                it.chaletAvailableDays.toString().toLowerCase(Locale.getDefault()).contains(query)
                    }?.let {
                        list.addAll(it)
                    }
                } else {
                    unfilteredList?.let { list.addAll(it) }
                }
                submitList(list)
    }


}

class OnClickListener(val clickListener: (chaletsProperties:ChaletsProperties) -> Unit) {
    fun onClick(chaletsProperties:ChaletsProperties) = clickListener(chaletsProperties)
}