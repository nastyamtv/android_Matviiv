package com.example.controlssample

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var mSettings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = findViewById<EditText>(R.id.user_name)
        userName.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Toast.makeText(applicationContext, userName.text, Toast.LENGTH_SHORT).show()
                return@setOnKeyListener true
            }
            false
        }

        mSettings = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val toggleButton = findViewById<ToggleButton>(R.id.toggle_button)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val editText = findViewById<EditText>(R.id.user_name)

        checkBox.isChecked = mSettings?.getBoolean(getString(R.string.SAVED_CHECK_BOX_KEY), false) ?: false
        toggleButton.isChecked = mSettings?.getBoolean(getString(R.string.SAVED_TOGGLE_BUTTON_KEY), false) ?: false
        radioGroup.check(mSettings?.getInt(getString(R.string.SAVED_RADIO_GROUP_KEY), -1) ?: -1)
        editText.setText(mSettings?.getString(getString(R.string.SAVED_EDIT_TEXT_KEY), ""))
    }

    override fun onPause() {
        super.onPause()
        val editor = mSettings?.edit()
        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val toggleButton = findViewById<ToggleButton>(R.id.toggle_button)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val editText = findViewById<EditText>(R.id.user_name)

        editor?.putBoolean(getString(R.string.SAVED_CHECK_BOX_KEY), checkBox.isChecked)
        editor?.putBoolean(getString(R.string.SAVED_TOGGLE_BUTTON_KEY), toggleButton.isChecked)
        editor?.putInt(getString(R.string.SAVED_RADIO_GROUP_KEY), radioGroup.checkedRadioButtonId)
        editor?.putString(getString(R.string.SAVED_EDIT_TEXT_KEY), editText.text.toString())
        editor?.apply()
    }

    fun onButtonClicked(v: View?) {
        Toast.makeText(this, "Кнопка натиснута", Toast.LENGTH_SHORT).show()
    }

    fun onClearButtonClicked(v: View?) {
        val userName = findViewById<EditText>(R.id.user_name)
        userName.setText("")
    }

    fun onCheckboxClicked(v: View) {
        val checkBox = v as CheckBox
        val message = if (checkBox.isChecked) "Відмічено" else "Не відмічено"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onToggleClicked(v: View) {
        val toggleButton = v as ToggleButton
        val message = if (toggleButton.isChecked) "Увімкнено" else "Вимкнено"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onRadioButtonClicked(v: View) {
        val radioButton = v as RadioButton
        Toast.makeText(this, "Вибраний звір: ${radioButton.text}", Toast.LENGTH_SHORT).show()
    }
}
