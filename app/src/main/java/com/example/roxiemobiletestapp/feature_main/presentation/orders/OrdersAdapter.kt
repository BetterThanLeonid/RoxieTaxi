/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.presentation.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roxiemobiletestapp.core.utils.extensions.toDate
import com.example.roxiemobiletestapp.databinding.ContainerOrderItemBinding
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun onItemClick(listener: OnItemClickListener) {
        this.listener = listener
    }

    var data: List<OrderModel> = listOf()
        set(value) {
            field = value
            // TODO: Change to DiffUtil
            notifyDataSetChanged()
        }

    class ViewHolder(
        private val binding: ContainerOrderItemBinding,
        private val listener: OnItemClickListener?,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderModel) {
            binding.startAddress.text = item.startAddress
            binding.endAddress.text = item.endAddress
            binding.date.text = item.orderTime.toDate()
            binding.amount.text = item.amount
            binding.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ContainerOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}
