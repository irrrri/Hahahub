package com.hahahub.di

import com.hahahub.di.module.DatabaseModule
import com.hahahub.di.module.RepositoryModule
import com.hahahub.startup.MainActivity
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
