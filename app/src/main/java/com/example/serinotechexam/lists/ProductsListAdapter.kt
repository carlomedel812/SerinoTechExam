package com.example.serinotechexam.lists

import android.media.browse.MediaBrowser.ItemCallback
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serinotechexam.R
import com.example.serinotechexam.databinding.ItemProductBinding
import com.example.serinotechexam.model.Product

class ProductsListAdapter(private val listener: Listener) : PagingDataAdapter<Product, ProductsListAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

        override fun getChangePayload(oldItem: Product, newItem: Product): Any? {
            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }


    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Glide.with(imageviewThumbnail)
                .load(product.thumbnail)
                .placeholder(R.color.light_grey)
                .error(R.color.light_grey)
                .into(imageviewThumbnail)

            textviewTitle.text = product.title
            textviewDescription.text = product.description
            textviewPrice.text = product.price.toString()
            textviewPrice.text = "Php ${product.price}"

            root.setOnClickListener {
                listener.onProductClicked(product)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as Product
            holder.bind(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemProductBinding.inflate(inflater, parent, false))
    }

    interface Listener {
        fun onProductClicked(product: Product)
    }
}