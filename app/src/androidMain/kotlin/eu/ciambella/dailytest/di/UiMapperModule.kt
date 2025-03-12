package eu.ciambella.dailytest.di

import eu.ciambella.dailytest.present.common.formatter.DateFormatter
import eu.ciambella.dailytest.present.common.mapper.ErrorPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.ScaffoldPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.TopAppBarPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.VideoPropertyMapper
import eu.ciambella.dailytest.present.screen.home.HomeScreenMapper
import eu.ciambella.dailytest.present.screen.player.PlayerScreenMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiMapperModule = module {
    singleOf(::DateFormatter)
    singleOf(::ScaffoldPropertyMapper)
    singleOf(::TopAppBarPropertyMapper)
    singleOf(::VideoPropertyMapper)
    singleOf(::HomeScreenMapper)
    singleOf(::PlayerScreenMapper)
    singleOf(::ErrorPropertyMapper)
}
