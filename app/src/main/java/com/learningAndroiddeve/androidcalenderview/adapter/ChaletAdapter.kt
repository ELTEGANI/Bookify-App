package com.learningAndroiddeve.androidcalenderview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningAndroiddeve.androidcalenderview.data.ChaletsProperties
import com.learningAndroiddeve.androidcalenderview.databinding.ChaletViewItemBinding


class ChaletAdapter() : ListAdapter<ChaletsProperties, ChaletAdapter.ChaletPropertyViewHolder>(DiffCallback) {

    class ChaletPropertyViewHolder(private var binding: ChaletViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chaletsProperties : ChaletsProperties) {
            binding.chalet = chaletsProperties
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaletPropertyViewHolder {
        return ChaletPropertyViewHolder(ChaletViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ChaletPropertyViewHolder, position: Int) {
        val chaletsProperty = getItem(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(imagesProperty)
//        }
        holder.bind(chaletsProperty)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ChaletsProperties>() {
        override fun areItemsTheSame(oldItem: ChaletsProperties, newItem: ChaletsProperties): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ChaletsProperties, newItem: ChaletsProperties): Boolean {
            return oldItem.chaletName == newItem.chaletName
        }
    }

    class OnClickListener(val clickListener: (chaletsProperties:ChaletsProperties) -> Unit) {
        fun onClick(chaletsProperties:ChaletsProperties) = clickListener(chaletsProperties)
    }
}