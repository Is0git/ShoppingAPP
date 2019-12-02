package com.example.shoppingapp.ui.fragments.items_fragment

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.databinding.ItemsListBinding
import com.example.shoppingapp.di.activity.items_fragment.ItemsScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val BY_PRICE_FROM_LOWEST = 1
const val BY_PRICE_FROM_HIGHEST = 2
const val BY_NAME = 3
const val BY_TYPE = 4

@ItemsScope
class ItemAdapter @Inject constructor() : ListAdapter<Item, ItemAdapter.MyViewHolder>(asyncDiffer) {

    lateinit var listener: ItemListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.MyViewHolder {
        val binding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .also { it.listener = listener }
        return MyViewHolder(binding, currentList)
    }

    override fun onBindViewHolder(holder: ItemAdapter.MyViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }

    suspend fun filter(direction: Int) = coroutineScope {
        launch(Dispatchers.Default) {

            val sorted = when (direction) {
                BY_PRICE_FROM_HIGHEST -> {
                    currentList.sortedByDescending { it.price }
                }

                BY_PRICE_FROM_LOWEST -> {
                    currentList.sortedBy { it.price }
                }
                BY_NAME -> {
                    currentList.sortedBy { it.title }
                }

                BY_TYPE -> {
                    currentList.sortedBy { it.type }
                }
                else -> null
            }
            resetList(sorted)

        }
    }

    suspend fun resetList(sorted: List<Item>?) {
        withContext(Dispatchers.Main) {
            submitList(sorted)
        }
    }

    class MyViewHolder(val binding: ItemsListBinding, val items: List<Item>) :
        RecyclerView.ViewHolder(binding.root),
        View.OnTouchListener, GestureDetector.OnGestureListener, View.OnDragListener {

        val metrics = DisplayMetrics()
        val gestureDetector = GestureDetector(binding.root.context, this)

        init {
            binding.mainCard.setOnTouchListener(this)
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (v?.id == R.id.mainCard) {
                Log.d("ItemAdapter", "TOUCH")

                gestureDetector.onTouchEvent(event)
                return true
            }
            return false
        }

        override fun onShowPress(e: MotionEvent?) {
            Log.d("ItemAdapter", "ON SHOW PRESS")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.d("ItemAdapter", "singleTapUp")
            binding.listener?.onItemClick(binding.item!!, binding)
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            Log.d("ItemAdapter", "ON DOWN")
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d("ItemAdapter", "FLIND")
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.d("ItemAdapter", "SCROLL")
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            val item = if (adapterPosition > 0) items[adapterPosition] else items[0]
            val builder = SmallerDragShadow(binding.itemImage)
            val clipDataItem = ClipData.Item(item.id.toString())
            val clipdata =
                ClipData("itemID", arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), clipDataItem)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.mainCard.startDragAndDrop(clipdata, builder, null, 0)
            } else {
                binding.mainCard.startDrag(clipdata, builder, null, 0)
            }
            builder.view.setOnDragListener(this)
        }


        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            Log.d("ItemAdapter", "DRAG")
            return when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    Log.d("ItemAdapter", "STARTED")
                    true
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    Log.d("ItemAdapter", "ENTER")
                    true
                }

                DragEvent.ACTION_DRAG_LOCATION -> {
                    Log.d("ItemAdapter", "LOCAITON")
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    Log.d("ItemAdapter", "EXITED")
                    true
                }
                DragEvent.ACTION_DROP -> {
                    Log.d("ItemAdapter", "DROP2: ${v?.id}")
                    true
                }
                else -> {
                    Log.d("ItemAdapter", "??")
                    false
                }
            }

//        if (event.y < metrics.heightPixels - 200)
        }


    }
}

val callback = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

}

val asyncDiffer = AsyncDifferConfig.Builder<Item>(callback).build()