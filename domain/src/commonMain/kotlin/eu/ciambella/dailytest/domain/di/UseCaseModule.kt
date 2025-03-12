package eu.ciambella.dailytest.domain.di

import eu.ciambella.dailytest.domain.network.NetworkAvailableUseCase
import eu.ciambella.dailytest.domain.video.GetVideoUrlUseCase
import eu.ciambella.dailytest.domain.video.GetVideosUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetVideosUseCase)
    singleOf(::GetVideoUrlUseCase)
    singleOf(::NetworkAvailableUseCase)
}
