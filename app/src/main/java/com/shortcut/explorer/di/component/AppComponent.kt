package com.shortcut.explorer.di.component

import android.content.Context
import com.shortcut.explorer.App
import com.shortcut.explorer.di.module.ActivityBuilderModule
import com.shortcut.explorer.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AndroidInjector<App>
    }

}