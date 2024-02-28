package com.carlosprieto.randomuser.data.module

import com.carlosprieto.randomuser.data.core.RetrofitClient
import org.koin.dsl.module

val ApiModule = module {
    single { RetrofitClient.initRetrofit }
}