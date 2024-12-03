package com.hahahub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hahahub.R
import com.hahahub.ui.joke_list.JokesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, JokesListFragment())
                .commit()
        }
    }
}