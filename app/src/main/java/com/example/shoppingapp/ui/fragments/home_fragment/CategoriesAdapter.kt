package com.example.shoppingapp.ui.fragments.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.models.Categories
import com.example.shoppingapp.databinding.CategoryListBinding
import com.example.shoppingapp.di.activity.home_fragment.HomeFragmentScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HomeFragmentScope
class CategoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    lateinit var listener: OnCategoryListener
    var items = listOf<Categories>()
    var defaultItems = mutableListOf<Categories>()
    var areDefaultItemsSet: Boolean = false

    class MyViewHolder(val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                .also { it.clickListener = listener }

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.category = items[position]
    }

    suspend fun filter(text: String?) {
        coroutineScope {
            launch(Dispatchers.Default) {
                if (!areDefaultItemsSet) defaultItems.addAll(items).also {
                    areDefaultItemsSet = true
                }

                if (!text.isNullOrBlank()) {
                    items = defaultItems.filter {
                        it.type.toLowerCase(Locale.ROOT).contains(
                            text.toLowerCase(
                                Locale.ROOT
                            )
                        )
                    }
                    launch(Dispatchers.Main) { this@CategoriesAdapter.notifyDataSetChanged() }
                } else items =
                    defaultItems.also { launch(Dispatchers.Main) { notifyDataSetChanged() } }
            }

        }

    }

    override fun getItemCount(): Int = items.size

}


val diffUtilCallback = object : DiffUtil.ItemCallback<Categories>() {
    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean =
        oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean =
        oldItem == newItem
}

val asyncDiffer = AsyncDifferConfig.Builder<Categories>(diffUtilCallback).build()