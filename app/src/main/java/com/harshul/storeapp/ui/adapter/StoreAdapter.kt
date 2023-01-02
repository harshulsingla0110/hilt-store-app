package com.harshul.storeapp.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshul.storeapp.data.models.Product
import com.harshul.storeapp.databinding.ItemStoreBinding

class StoreAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<StoreAdapter.MyViewHolder>() {

    private val colorLight = arrayOf("#FADBD8", "#D6EAF8", "#D5F5E3", "#FCF3CF", "#FAE5D3")
    private val colorDark = arrayOf("#E74C3C", "#3498DB", "#2ECC71", "#F1C40F", "#E67E22")
    private var colorPosition = 0

    inner class MyViewHolder(val binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = list[position]
        holder.binding.apply {

            Glide.with(imageView.context).load(product.image)
                .into(imageView);

            tvCategory.text = product.category
            colorPosition = position % colorLight.size
            tvCategory.setTextColor(Color.parseColor(colorDark[colorPosition]))
            tvCategory.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(colorLight[colorPosition]))

            tvTitle.text = product.title
            tvDesc.text = product.description
            tvPrice.text = "$${product.price}"

        }
    }
}