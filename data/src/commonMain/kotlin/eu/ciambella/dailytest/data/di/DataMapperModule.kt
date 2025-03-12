package eu.ciambella.dailytest.data.di

import eu.ciambella.dailytest.data.repository.video.mapper.VideosResponseMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataMapperModule = module {
    singleOf(::VideosResponseMapper)
}
