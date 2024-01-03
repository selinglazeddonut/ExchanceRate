package com.example.currencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.databinding.RowLayoutBinding
import com.example.currencyapp.model.CurrencyModel


class RecyclerViewAdapter(
    private val currencyList: ArrayList<CurrencyModel>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(currencyModel: CurrencyModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowHolder {
        val itemBinding =
            RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(itemBinding)

    }


    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return currencyList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClick(currencyList.get(position))

            holder.binding.textName.text = currencyList.get(position).currency
            holder.binding.textPrice.text = currencyList.get(position).price
        }


    }
}