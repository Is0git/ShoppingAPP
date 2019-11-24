package com.example.shoppingapp.ui.fragments.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.shoppingapp.data.models.Categories
import com.example.shoppingapp.databinding.CategoryListBinding
import com.example.shoppingapp.di.activity.home_fragment.HomeFragmentScope
import javax.inject.Inject

@HomeFragmentScope
class CategoriesAdapter @Inject constructor(): ListAdapter<Categories, CategoriesAdapter.MyViewHolder>(asyncDiffer) {
    lateinit var listener: OnCategoryListener
    class MyViewHolder(val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false).also { it.clickListener = listener }

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.category = getItem(position)
    }
}

val diffUtilCallback = object : DiffUtil.ItemCallback<Categories>() {
    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean =
        oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean =
        oldItem == newItem
}

val asyncDiffer = AsyncDifferConfig.Builder<Categories>(diffUtilCallback).build()