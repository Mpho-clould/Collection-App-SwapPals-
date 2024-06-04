 package com.example.swappals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import com.example.swappals.adapter.ItemAdapter
import com.example.swappals.model.Category
import com.example.swappals.model.Item
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.collections.Collection

 class Add_item : AppCompatActivity() {
     private val items = mutableListOf<Item>()

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_add_item)


         val linearLayoutItems: LinearLayout = findViewById(R.id.linearLayoutItems)
         val listViewItems = findViewById<ListView>(R.id.listViewItems)

         val categoryName = intent.getStringExtra("category")
         val categories = intent.getSerializableExtra("categories") as Array<Category>?

         if (categoryName == null || categories == null) {
             // Handle the case where the category name or categories array is not passed correctly
             // Show an error message or return to the previous activity
             return
         }

         val category = categories.find { it.name == categoryName }
         if (category == null) {
             // Handle the case where the category object is not found for the given category name
             // Show an error message or return to the previous activity
             return
         }

         val categoryItems = items.filter { it.category == category }
         val itemAdapter = ItemAdapter(this, categoryItems)
         listViewItems.adapter = itemAdapter

         linearLayoutItems.visibility = View.VISIBLE

         val editTextItemTitle = findViewById<EditText>(R.id.editTextItemTitle)
         val editTextItemDescription = findViewById<EditText>(R.id.editTextItemDescription)
         val imageViewItemImage = findViewById<ImageView>(R.id.imageViewItemImage)
         val buttonAddItem = findViewById<Button>(R.id.buttonAddItem)

         buttonAddItem.setOnClickListener {
             val itemTitle = editTextItemTitle.text.toString()
             val itemDescription = editTextItemDescription.text.toString()


             val item = Item(
                 title = itemTitle,
                 category = category,
                 description = itemDescription,
                 image = imageViewItemImage.drawable
             )

             items.add(item)
             // Notify the adapter that the data has changed.
             itemAdapter.notifyDataSetChanged()
             editTextItemTitle.text.clear()
             editTextItemDescription.text.clear()

             Toast.makeText(
                 this,
                 "Item added successfully to category: ${category.name}",
                 Toast.LENGTH_SHORT
             ).show()
         }

         val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
         bottomNavigationView.selectedItemId = R.id.bottom_home
         bottomNavigationView.setOnItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.bottom_home -> {
                     startActivity(Intent(applicationContext, User_Dashboard::class.java))
                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                     finish()
                     true
                 }

                 R.id.bottom_collection -> {
                     startActivity(Intent(applicationContext, Collection::class.java))
                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                     finish()
                     true
                 }

                 R.id.bottom_add -> {
                     startActivity(Intent(applicationContext, Categories::class.java))
                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                     finish()
                     true
                 }

                 R.id.bottom_profile -> {
                     startActivity(Intent(applicationContext, Profile::class.java))
                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                     finish()
                     true
                 }

                 R.id.bottom_logout -> {
                     startActivity(Intent(applicationContext, Login::class.java))
                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                     finish()
                     true
                 }

                 else -> false
             }
         }
     }
 }
