package com.example.di.di

import android.app.Application
import com.example.di.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
     interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder
        fun Build() : AppComponent
}
}