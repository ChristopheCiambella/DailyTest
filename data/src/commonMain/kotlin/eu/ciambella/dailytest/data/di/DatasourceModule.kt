package eu.ciambella.dailytest.data.di

import eu.ciambella.dailytest.data.repository.video.datasource.FetchVideoApiDatasource
import eu.ciambella.dailytest.data.repository.video.datasource.HardcodedVideoUrlDatasource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datasourceModule = module {
    singleOf(::FetchVideoApiDatasource)
    singleOf(::HardcodedVideoUrlDatasource)
}
