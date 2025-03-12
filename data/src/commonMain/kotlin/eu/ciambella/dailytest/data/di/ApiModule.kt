package eu.ciambella.dailytest.data.di

import eu.ciambella.dailytest.data.api.DailymotionApi
import eu.ciambella.dailytest.data.api.DailymotionHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule = module {
    singleOf(::DailymotionHttpClient)
    singleOf(::DailymotionApi)
}
