package com.example.git_users.di

import com.example.git_users.data.GithubRepository
import com.example.git_users.data.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repo: GithubRepository) : IRepository
}