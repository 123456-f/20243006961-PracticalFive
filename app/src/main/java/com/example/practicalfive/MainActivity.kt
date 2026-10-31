package com.example.practicalfive

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MUNICIPALITY_NAME = "municipality_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMunicipalityInput: EditText = findViewById(R.id.et_municipality_input)
        val textProcessor = TextProcessor()

        etMunicipalityInput.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE
                || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {

                val rawInput = etMunicipalityInput.text.toString()
                val normalizedName = textProcessor.processMunicipality(rawInput)

                if (normalizedName == null) {
                    Toast.makeText(
                        this,
                        "Invalid input! Please enter Beijing, Shanghai, Tianjin or Chongqing",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(this, Municipality::class.java)
                    intent.putExtra(EXTRA_MUNICIPALITY_NAME, normalizedName)
                    startActivity(intent)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}