package eu.ciambella.dailytest.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import eu.ciambella.dailytest.data.di.apiModule
import eu.ciambella.dailytest.data.di.dataMapperModule
import eu.ciambella.dailytest.data.di.datasourceModule
import eu.ciambella.dailytest.data.di.datastoreModule
import eu.ciambella.dailytest.data.di.repositoryModule
import eu.ciambella.dailytest.domain.di.useCaseModule
import eu.ciambella.dailytest.domain.network.NetworkAvailableUseCase
import eu.ciambella.dailytest.di.appModule
import eu.ciambella.dailytest.di.presentModule
import eu.ciambella.dailytest.di.uiMapperModule
import eu.ciambella.dailytest.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DailyTestApplication : Application(), Application.ActivityLifecycleCallbacks {

    private val networkAvailableUseCase: NetworkAvailableUseCase by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        registerActivityLifecycleCallbacks(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@DailyTestApplication)
            modules(
                listOf(
                    useCaseModule,
                    appModule,
                    apiModule,
                    datasourceModule,
                    datastoreModule,
                    dataMapperModule,
                    repositoryModule,
                    viewModelModule,
                    uiMapperModule,
                    presentModule,
                )
            )
        }
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?,
    ) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPostResumed(activity: Activity) {
        super.onActivityPostResumed(activity)
        networkAvailableUseCase.onApplicationEnterInForeground()
    }

    override fun onActivityPrePaused(activity: Activity) {
        super.onActivityPrePaused(activity)
        networkAvailableUseCase.onApplicationEnterInBackground()
    }

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(
        activity: Activity,
        savedInstanceState: Bundle
    ) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit

}
