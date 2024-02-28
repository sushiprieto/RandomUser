package com.carlosprieto.randomuser.data.module

import com.carlosprieto.randomuser.data.HomeDataSource
import com.carlosprieto.randomuser.data.UserHandler
import com.carlosprieto.randomuser.data.UserHandlerInterface
import org.koin.dsl.module

val UsersModule = module {
    single<UserHandlerInterface>() { UserHandler(get()) }
    single { HomeDataSource(get()) }
}