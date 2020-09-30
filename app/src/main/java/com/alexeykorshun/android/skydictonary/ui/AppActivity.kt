package com.alexeykorshun.android.skydictonary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexeykorshun.android.skydictonary.R
import com.alexeykorshun.android.skydictonary.ui.list.ListFragment

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        if (savedInstanceState == null && supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ListFragment())
                .commit()
        }
    }
}