package com.example.webviewsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Кнопка для переходу до WebViewSampleActivity
        val openWebViewButton = findViewById<Button>(R.id.openWebViewButton)
        openWebViewButton.setOnClickListener {
            val intent = Intent(this, WebViewSampleActivity::class.java)
            startActivity(intent)
        }
    }
}
