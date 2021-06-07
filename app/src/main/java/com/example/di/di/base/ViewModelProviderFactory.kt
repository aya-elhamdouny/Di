package com.example.di.di.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

 @Singleton
 class ViewModelProviderFactory @Inject constructor(
     private val viewmodels : MutableMap<Class<out ViewModel> , Provider<ViewModel>>

) : ViewModelProvider.Factory {
     override fun <T : ViewModel?> create(modelClass: Class<T>): T =
         viewmodels[modelClass]?.get() as T


 }