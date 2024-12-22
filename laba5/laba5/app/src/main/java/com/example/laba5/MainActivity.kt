package com.example.laba5

import android.app.TabActivity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.TabHost

@Suppress("deprecation")
class MainActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res: Resources = resources
        val tabHost: TabHost = tabHost

        // Створення вкладки для Students
        var intent = Intent().setClass(this, StudentsActivity::class.java)
        var spec = tabHost.newTabSpec("students")
            .setIndicator(res.getString(R.string.tab1_indicator))
            .setContent(intent)
        tabHost.addTab(spec)

        // Створення вкладки для Teachers
        intent = Intent().setClass(this, TeachersActivity::class.java)
        spec = tabHost.newTabSpec("teachers")
            .setIndicator(res.getString(R.string.tab2_indicator))
            .setContent(intent)
        tabHost.addTab(spec)

        // Створення вкладки для Classes
        intent = Intent().setClass(this, ClassesActivity::class.java)
        spec = tabHost.newTabSpec("classes")
            .setIndicator(res.getString(R.string.tab3_indicator))
            .setContent(intent)
        tabHost.addTab(spec)

        tabHost.currentTab = 0 // Встановити вкладку за замовчуванням
    }
}
