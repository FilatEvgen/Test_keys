package ru.kosproger.testkeys

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val entranceFragment = EntranceFragment.EntranceFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, entranceFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}