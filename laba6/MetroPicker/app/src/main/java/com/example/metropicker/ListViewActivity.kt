package com.example.metropicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    private lateinit var stationListView: ListView
    private lateinit var stationList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        stationList = resources.getStringArray(R.array.stations)

        stationListView = findViewById(R.id.stationListView)
        val adapter = ArrayAdapter(this, R.layout.list_item, stationList)
        stationListView.adapter = adapter

        stationListView.setOnItemClickListener { parent, view, position, id ->
            val selectedStation = stationList[position]
            val resultIntent = Intent()
            resultIntent.putExtra("selectedStation", selectedStation)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()  // Закриваємо активність і повертаємо вибрану станцію
        }
    }
}