package dev.blakeharris.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Provides repository singletons. Each `@Provides` returns the public interface so the `internal`
 * implementation is never leaked.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DataModule
