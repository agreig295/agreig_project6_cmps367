package com.example.scheduler2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_tips.*

class TipsActivity : AppCompatActivity() {

    //lists that make up the to-do list
    lateinit var titleList: ArrayList<String?>
    lateinit var descriptionList: ArrayList<String?>
    lateinit var priorityList: ArrayList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "TipsActivity_onCreate")
        setContentView(R.layout.activity_tips)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "TipsActivity_onStart")

        //keeping track of the lists so they can be passed to the next activity
        titleList=intent.getStringArrayListExtra("titleList")
        descriptionList=intent.getStringArrayListExtra("descriptionList")
        priorityList=intent.getStringArrayListExtra("priorityList")

        //loading the website
        webView.loadUrl("https://www.mindtools.com/pages/article/newHTE_07.htm")

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
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("titleList", titleList)
                    intent.putExtra("descriptionList", descriptionList)
                    intent.putExtra("priorityList", priorityList)
                    startActivity(intent)
                    true
                }
                R.id.bottom_nav_tips -> {
                    true
                }
                else -> false
            }
        }
    }
}