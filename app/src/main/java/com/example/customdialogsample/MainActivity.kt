package com.example.customdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            confirmFireMissiles()
        }

    }

    fun confirmFireMissiles() {
        val newFragment = FireMissilesDialogFragment()
        newFragment.show(supportFragmentManager, "missiles")
    }
}

// 出典：https://developer.android.com/guide/topics/ui/dialogs?hl=ja