package com.example.di.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.di.di.base.ViewModelKey
import com.example.di.di.base.ViewModelProviderFactory
import com.example.di.ui.movie.MovieViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
     abstract fun bindMovieViewModel(movieViewModel : MovieViewModel): ViewModel



}

