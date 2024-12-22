package com.example.listviewsample

import android.app.ListActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast

class MainActivity : ListActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val r = resources
        val stationsArray = r.getStringArray(R.array.stations)
        val aa = ArrayAdapter(this, R.layout.list_item, stationsArray)
        listAdapter = aa
        val lv = listView
        val duration = Toast.LENGTH_LONG
        val context = applicationContext
        val toast = Toast.makeText(context, intent.action, duration)
        toast.show()
        lv.onItemClickListener =
            OnItemClickListener { parent, v, position, id ->
                val text = (v as TextView).text
                val duration = Toast.LENGTH_LONG
                val context = applicationContext
                Toast.makeText(context, text, duration).show()
            }
    }
}