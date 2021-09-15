package com.shortcut.explorer.di.module

import com.shortcut.explorer.di.module.data.DatabaseModule
import com.shortcut.explorer.di.module.data.NetworkModule
import com.shortcut.explorer.di.module.data.RepositoryModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
    ]
)
class DataModule