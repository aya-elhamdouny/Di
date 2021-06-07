package com.example.di.di.modules

import com.example.di.ui.detail.DetailsFragment
import com.example.di.ui.movie.MovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment() : MovieFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailListFragment() : DetailsFragment
}