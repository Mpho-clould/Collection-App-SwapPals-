package com.example.swappals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.swappals.adapter.CategoryAdapter
import com.example.swappals.model.Category
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.collections.Collection

class Categories : AppCompatActivity() {
    private val categories = mutableListOf<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)


        val editTextCategoryName =
            findViewById<EditText>(R.id.editTextCategoryName)
        val editTextSetGoal = findViewById<EditText>(R.id.editTextSetGoal)
        val buttonCreateCategory =
            findViewById<Button>(R.id.buttonCreateCategory)
        val listViewCategories =
            findViewById<ListView>(R.id.listViewCategories)


        //create the adapter.
        val categoryAdapter = CategoryAdapter(this, categories)

        // Set the adapter on the ListView.
        listViewCategories.adapter = categoryAdapter

        // Set up the listeners.
        buttonCreateCategory.setOnClickListener {
            // Get the category name from the edit text.
            val categoryName = editTextCategoryName.text.toString()
            val setGoal = editTextSetGoal.text.toString()



            //validate if the field is empty
            if (categoryName.isEmpty()) {
                editTextCategoryName.error = "Field can't be empty, Category Required"
                return@setOnClickListener
            }
            val goalItemCount = setGoal.toIntOrNull()
            if(goalItemCount == null || goalItemCount <=0){
                editTextSetGoal.error = "Invalid goal, please set a goal!!!"
                return@setOnClickListener
            }

            // Create a new category object.
            val category = Category(name = categoryName, goalItem = goalItemCount)
            // Add the category to the list.
            categories.add(category)
            // Notify the adapter that the data has changed.
            categoryAdapter.notifyDataSetChanged()
            // Display a message to the user.
            Toast.makeText(
                this, "Category created successfully!",
                Toast.LENGTH_SHORT
            ).show()

            // Clear the category name
            editTextCategoryName.setText("")
            // Clear the goal set
            editTextSetGoal.setText("")

        }
        listViewCategories.setOnItemClickListener { parent, view, position, id ->
            val category = categories[position].name

            // You can pass the selected category to the ItemActivity using Intent and startActivity
            //Example:
            val intent = Intent(this, Add_item::class.java)
            intent.putExtra("category", category)
            intent.putExtra("categories", categories.toTypedArray())
            startActivity(intent)
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