package com.example.scheduler2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.bottomNav

class AddActivity : AppCompatActivity() {

    //lists that make up the to-do list
    lateinit var titleList: ArrayList<String?>
    lateinit var descriptionList: ArrayList<String?>
    lateinit var priorityList: ArrayList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "AddActivity_onCreate")
        setContentView(R.layout.activity_add)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "AddActivity_onStart")

        //restoring the lists
        titleList=intent.getStringArrayListExtra("titleList")
        descriptionList=intent.getStringArrayListExtra("descriptionList")
        priorityList=intent.getStringArrayListExtra("priorityList")

        //adding the new item into the to-do list
        AddActivity_addButton.setOnClickListener {
            titleList.add(AddActivity_getTitle.text.toString())
            descriptionList.add(AddActivity_getDescription.text.toString())
            priorityList.add(AddActivity_priorityMenu.selectedItem.toString())

            organize()
        }

        //listening for the user's selection and starting the chosen activity
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_add -> {
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

    /* Function: organize()
     * Inputs: no input
     * Outputs: no output
     * Description: organizes the to-do list by priority by finding the index that the new item should placed at in the list and updating each of the 3 lists
     */
    fun organize() {
        val indexToPlace: Int = priorityList.lastIndexOf(priorityList[priorityList.lastIndex]) + 1

        titleList.add(indexToPlace, titleList[titleList.lastIndex])
        descriptionList.add(indexToPlace, descriptionList[descriptionList.lastIndex])
        priorityList.add(indexToPlace, priorityList[priorityList.lastIndex])

        titleList.removeAt(titleList.lastIndex)
        descriptionList.removeAt(descriptionList.lastIndex)
        priorityList.removeAt(priorityList.lastIndex)
    }
}
