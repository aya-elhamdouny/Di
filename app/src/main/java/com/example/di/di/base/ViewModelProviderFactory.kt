package com.example.di.di.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

 class ViewModelProviderFactory @Inject constructor(
     private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

) : ViewModelProvider.Factory {
     override fun <T : ViewModel?> create(modelClass: Class<T>): T =
         providers[modelClass]?.get() as T


 }