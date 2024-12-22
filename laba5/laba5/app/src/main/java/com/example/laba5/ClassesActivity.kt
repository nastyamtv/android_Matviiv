package com.example.laba5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClassesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentText = getString(R.string.tab3_content)
        val textView = TextView(this)
        textView.text = contentText
        setContentView(textView)
    }
}
