package com.example.di.ui.movie

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.di.R
import com.example.di.ui.BaseActivity
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) }
}