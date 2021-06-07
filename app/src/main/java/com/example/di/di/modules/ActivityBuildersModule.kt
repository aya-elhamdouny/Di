package com.example.di.di.modules

import com.example.di.ui.movie.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class ActivityBuildersModule {

     @ContributesAndroidInjector(modules = [FragmentBuilderModule::class , ViewModelModule::class])
     abstract fun contributeMainActivity(): MainActivity

}