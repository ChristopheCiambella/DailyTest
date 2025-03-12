package eu.ciambella.dailytest.di

import eu.ciambella.dailytest.common.resource.DrawableResourcesImpl
import eu.ciambella.dailytest.common.resource.ResourcesProvider
import eu.ciambella.dailytest.common.resource.ResourcesProviderImpl
import eu.ciambella.dailytest.common.resource.StringsResourcesImpl
import eu.ciambella.dailytest.present.resource.DrawableResources
import eu.ciambella.dailytest.present.resource.StringResources
import org.koin.dsl.module

val presentModule = module {
    single<ResourcesProvider> { ResourcesProviderImpl(get()) }
    single<StringResources> { StringsResourcesImpl(get()) }
    single<DrawableResources> { DrawableResourcesImpl() }
}
