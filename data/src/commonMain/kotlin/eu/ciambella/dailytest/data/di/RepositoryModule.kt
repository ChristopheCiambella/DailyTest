package eu.ciambella.dailytest.data.di

import eu.ciambella.dailytest.data.repository.network.NetworkRepositoryImpl
import eu.ciambella.dailytest.data.repository.video.VideoRepositoryImpl
import eu.ciambella.dailytest.domain.network.NetworkRepository
import eu.ciambella.dailytest.domain.video.VideoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VideoRepository> { VideoRepositoryImpl(get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
}
