package com.example.shoppingapp.ui.fragments.items_fragment

import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.databinding.ItemsListBinding

interface ItemListener {
    fun onItemClick(item: Item, binding: ItemsListBinding)
}