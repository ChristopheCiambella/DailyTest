package eu.ciambella.dailytest.di

import eu.ciambella.dailytest.common.navigation.NavigationConsumer
import eu.ciambella.dailytest.domain.utils.CoroutineDispatcherProvider
import eu.ciambella.dailytest.present.navigation.ActionHandler
import eu.ciambella.dailytest.present.navigation.MainNavigator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MainNavigator)
    singleOf(::ActionHandler)
    singleOf(::NavigationConsumer)
    singleOf(::CoroutineDispatcherProvider)
}
