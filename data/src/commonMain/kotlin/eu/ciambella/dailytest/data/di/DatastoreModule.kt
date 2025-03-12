package eu.ciambella.dailytest.data.di

import eu.ciambella.dailytest.data.repository.network.datastore.NetworkDatastore
import eu.ciambella.dailytest.data.repository.video.datastore.VideoDatastore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datastoreModule = module {
    singleOf(::VideoDatastore)
    singleOf(::NetworkDatastore)
}
