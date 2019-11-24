package com.example.shoppingapp.ui.fragments.items_fragment

import android.graphics.Canvas
import android.graphics.Point
import android.view.View

class SmallerDragShadow(view: View) : View.DragShadowBuilder(view) {


    override fun onProvideShadowMetrics(outShadowSize: Point?, outShadowTouchPoint: Point?) {
        outShadowSize?.set(300, 300)
        outShadowTouchPoint?.set(150,150)
    }
}