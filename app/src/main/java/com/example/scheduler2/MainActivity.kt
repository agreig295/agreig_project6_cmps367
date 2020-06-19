// Alyssa Greig
// CMPS 367
// Project 6
// June 19, 2020

package com.example.scheduler2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //keeping track of each part of the to-do list in different ArrayLists
    lateinit var titleList: ArrayList<String?>
    lateinit var descriptionList: ArrayList<String?>
    lateinit var priorityList: ArrayList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "MainActivity_onCreate")
        setContentView(R.layout.activity_main)

        // to restore the list on restart
        titleList = savedInstanceState?.getStringArrayList("titles") as ArrayList<String?>
        descriptionList = savedInstanceState?.getStringArrayList("descriptions") as ArrayList<String?>
        priorityList = savedInstanceState?.getStringArrayList("priorities") as ArrayList<String?>
    }

    @SuppressLint("WrongConstant")
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity_onStart")

        // re-initializing the to-do list
        titleList=intent.getStringArrayListExtra("titleList")
        descriptionList=intent.getStringArrayListExtra("descriptionList")
        priorityList=intent.getStringArrayListExtra("priorityList")

        //getting recyclerview from xml
        val listRecyclerView = findViewById(R.id.listRecyclerView) as RecyclerView

        //adding a LayoutManager
        listRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //creating a CustomAdapter to display the to-do list
        val adapter = CustomAdapter(titleList, descriptionList, priorityList)

        //adding the adapter to the RecyclerView to display
        listRecyclerView.adapter = adapter

        //setting the default selected item in the BottomNavigation as the list button
        bottomNav.setSelectedItemId(R.id.bottom_nav_list)

        //listening for the user's selection and starting the chosen activity
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_add -> {
                    val intent = Intent(this, AddActivity::class.java)
                    intent.putExtra("titleList", titleList)
                    intent.putExtra("descriptionList", descriptionList)
                    intent.putExtra("priorityList", priorityList)
                    startActivity(intent)
                    true
                }
                R.id.bottom_nav_list -> {
                    true
                }
                R.id.bottom_nav_tips -> {
                    val intent = Intent(this, TipsActivity::class.java)
                    intent.putExtra("titleList", titleList)
                    intent.putExtra("descriptionList", descriptionList)
                    intent.putExtra("priorityList", priorityList)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // saving each list to restore on restart
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putStringArrayList("titles", titleList)
        outState.putStringArrayList("descriptions", descriptionList)
        outState.putStringArrayList("priorities", priorityList)
    }
}
