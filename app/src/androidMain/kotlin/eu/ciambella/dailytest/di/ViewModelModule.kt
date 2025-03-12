package eu.ciambella.dailytest.di

import eu.ciambella.dailytest.common.screen.home.HomeViewModel
import eu.ciambella.dailytest.screen.main.MainViewModel
import eu.ciambella.dailytest.common.screen.player.PlayerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::PlayerViewModel)
}
