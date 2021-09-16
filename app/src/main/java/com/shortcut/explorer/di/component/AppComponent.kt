package com.shortcut.explorer.di.component

import android.content.Context
import com.shortcut.explorer.App
import com.shortcut.explorer.di.module.ActivityBuilderModule
import com.shortcut.explorer.di.module.DataModule
import com.shortcut.explorer.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AndroidInjector<App>
    }

}