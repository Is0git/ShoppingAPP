package com.example.shoppingapp.util.databinding

import android.view.View
import android.widget.TextView
import com.example.shoppingapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object PricePlaceHolder {

    @JvmStatic
    fun getPriceString(view: View, price: Double) = view.context.getString(R.string.price, price)

    @JvmStatic
    suspend fun getFullPrice(text: TextView, prices: List<Double?>) = coroutineScope {
        launch(Dispatchers.Default) {
            var doubleResult: Double = 0.0
            val result = prices.forEach { doubleResult += it!! }
            val stringResult = text.context.getString(R.string.price, doubleResult)
            launch(Dispatchers.Main) {  text.text = stringResult}

        }
    }

}