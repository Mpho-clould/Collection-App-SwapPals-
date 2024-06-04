package com.example.swappals.model

import android.graphics.drawable.Drawable

class Item(
    val title: String, val description: String, val image:
    Drawable, val category: Category
) {
    var id: Long = 0
}