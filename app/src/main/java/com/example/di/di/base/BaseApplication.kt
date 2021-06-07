package com.example.di.di.base

import com.example.di.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class BaseApplication : DaggerApplication (){


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
      return DaggerAppComponent.builder().application(this).Build()   }
}