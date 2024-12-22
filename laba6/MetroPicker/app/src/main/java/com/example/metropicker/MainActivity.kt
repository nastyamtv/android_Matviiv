package com.example.metropicker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var selectedStationTextView: TextView
    private lateinit var openListViewButton: Button
    private lateinit var checkIntentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedStationTextView = findViewById(R.id.SelectedStationTextView)
        openListViewButton = findViewById(R.id.OpenListViewButton)
        checkIntentButton = findViewById(R.id.CheckIntentButton)

        openListViewButton.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            startActivityForResult(intent, 1)  // Запускаємо ListViewActivity
        }

        checkIntentButton.setOnClickListener {
            if (selectedStationTextView.text == getString(R.string.select_station)) {
                selectedStationTextView.text = "Ніякої станції не вибрано"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val selectedStation = data?.getStringExtra("selectedStation")
            selectedStationTextView.text = selectedStation ?: "Ніякої станції не вибрано"
        }
    }
}