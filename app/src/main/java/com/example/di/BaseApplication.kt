package com.example.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication (){


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
      //  return DaggerAppComponent.builder().application(this).build()
        TODO()
    }
}