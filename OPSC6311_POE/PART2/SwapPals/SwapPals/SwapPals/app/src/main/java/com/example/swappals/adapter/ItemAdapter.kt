package com.example.swappals.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.swappals.R
import com.example.swappals.model.Item

class ItemAdapter (private val context: Context, private val items: List<Item>) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun getView(position: Int, convertView: View?, parent:
    ViewGroup?): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.item_list_item, parent, false)
        val item = items[position]


        view.findViewById<TextView>(R.id.textViewName).text = item.title
        view.findViewById<TextView>(R.id.textViewCategory).text = item.category.name
        view.findViewById<TextView>(R.id.textViewDescription).text = item.description
        view.findViewById<ImageView>(R.id.imageViewItem).setImageDrawable(item.image)

        return view
    }
}