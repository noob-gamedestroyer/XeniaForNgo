package com.gamdestroyerr.xeniaforngo.di

import com.gamdestroyerr.xeniaforngo.repositories.DefaultMainRepository
import com.gamdestroyerr.xeniaforngo.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @ActivityScoped
    @Provides
    fun provideAuthRepository() = DefaultMainRepository() as MainRepository
}