package com.example.metropicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.laba6.R

class ListViewActivity : AppCompatActivity() {

    private var selectedStationName: String? = null
    private lateinit var mSettings: SharedPreferences
    private var resetSelectedStation = false
    private lateinit var toMainActivityIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val resources: Resources = resources
        val stationsArray = resources.getStringArray(R.array.stations)
        val adapter = ArrayAdapter(this, R.layout.list_item, stationsArray)
        val listView: ListView = findViewById(android.R.id.list)

        mSettings = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, v, position, id ->
            selectedStationName = (v as TextView).text.toString()
            val editor = mSettings.edit()
            editor.putString(getString(R.string.saved_selected_station_key), selectedStationName)
            editor.apply()
            toMainActivityIntent.putExtra("message_key", selectedStationName)
            startActivity(toMainActivityIntent)
        }

        registerForContextMenu(listView)
        resetSelectedStation = false
        val intent = intent
        selectedStationName = intent.getStringExtra("selected_station")
        toMainActivityIntent = Intent(applicationContext, MainActivity::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list_back_option -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_list_context_menu, menu)

        if (selectedStationName != null &&
            selectedStationName != getString(R.string.you_didnt_select_station) &&
            selectedStationName != getString(R.string.you_reseted_station) &&
            selectedStationName != getString(R.string.select_station) &&
            selectedStationName != "не обрано") {
            menu.getItem(1).title = "Обрана станція: $selectedStationName"
        } else {
            menu.getItem(1).title = "Станція не обрана"
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.selected_station_context -> true
            R.id.select_new_station_context -> {
                resetSelectedStation = true
                selectedStationName = null
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (resetSelectedStation) {
            selectedStationName = "Ви скинули станцію"
        } else {
            selectedStationName = "Ви не обрали станцію"
        }
        val editor = mSettings.edit()
        editor.putString(getString(R.string.saved_selected_station_key), null)
        editor.apply()
        toMainActivityIntent.putExtra("message_key", selectedStationName)
        startActivity(toMainActivityIntent)
    }
}
