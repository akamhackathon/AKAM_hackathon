package com.ryan.project.di

import com.ryan.project.authentication.repository.AuthRepo
import com.ryan.project.authentication.repository.DefaultAuthRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class AuthModule {

    @Singleton
    @Provides
    fun providesAuthRepository() = DefaultAuthRepo() as AuthRepo
}