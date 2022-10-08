package com.ryan.project.di

import com.ryan.project.mainactivityemployee.repository.DefaultEmployeeMainRepo
import com.ryan.project.mainactivityemployee.repository.EmployeeMainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmployeeMainModule {

    @Singleton
    @Provides
    fun providesEmployeeMainRepository() = DefaultEmployeeMainRepo() as EmployeeMainRepo

}