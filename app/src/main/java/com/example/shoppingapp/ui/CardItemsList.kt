package com.example.shoppingapp.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.databinding.CartItemsListBinding
import com.example.shoppingapp.di.activity.ActivityScope
import com.example.shoppingapp.ui.fragments.items_fragment.callback
import javax.inject.Inject

@ActivityScope
class CardItemsList @Inject constructor() : ListAdapter<FlattenItem, CardItemsList.MyViewHolder>(asyncDifferConfig) {
    lateinit var listener:MainActivityListener
    class MyViewHolder(val binding: CartItemsListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CartItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false).also { it.listener = listener }
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }
}

val diffCallback = object  : DiffUtil.ItemCallback<FlattenItem>() {
    override fun areItemsTheSame(oldItem: FlattenItem, newItem: FlattenItem): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: FlattenItem, newItem: FlattenItem): Boolean = oldItem == newItem

}

val asyncDifferConfig = AsyncDifferConfig.Builder<FlattenItem>(diffCallback).build()