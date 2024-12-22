package com.example.metropicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.laba6.R

class MainActivity : AppCompatActivity() {

    private lateinit var mSettings: SharedPreferences
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectedStationTextView: TextView = findViewById(R.id.SelectedStationTextView)
        val openListViewButton: Button = findViewById(R.id.OpenListViewButton)
        val checkIntentButton: Button = findViewById(R.id.CheckIntentButton)

        openListViewButton.setOnClickListener {
            val intent = Intent("com.example.metropicker.intent.action.PICK_METRO_STATION")
            val textView = findViewById<TextView>(R.id.SelectedStationTextView)
            if (textView.text != getString(R.string.you_didnt_select_station) &&
                textView.text != getString(R.string.select_station)) {
                intent.putExtra("selected_station", textView.text)
            } else {
                intent.putExtra("selected_station", "не обрано")
            }
            startActivity(intent)
        }

        checkIntentButton.setOnClickListener {
            val action = intent.action
            val data = intent.data
            val textView = findViewById<TextView>(R.id.SelectedStationTextView)
            textView.text = action
        }

        mSettings = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)
        intent = intent
        val defaultValue = getString(R.string.select_station)
        val str = mSettings.getString(getString(R.string.saved_selected_station_key), defaultValue)
        selectedStationTextView.text = str
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_exit_option -> {
                finishAffinity()
                System.exit(0)
                return true
            }
            R.id.main_reset_option -> {
                val editor = mSettings.edit()
                editor.putString(getString(R.string.saved_selected_station_key), null)
                editor.apply()
                val selectedStationTextView: TextView = findViewById(R.id.SelectedStationTextView)
                selectedStationTextView.text = getString(R.string.you_reseted_station)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val textView: TextView = findViewById(R.id.SelectedStationTextView)
        val str = intent.getStringExtra("message_key")
        str?.let { textView.text = it }
    }
}
