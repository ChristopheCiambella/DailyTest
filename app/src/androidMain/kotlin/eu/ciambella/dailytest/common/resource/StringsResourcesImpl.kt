package eu.ciambella.dailytest.common.resource

import eu.ciambella.dailytest.R
import eu.ciambella.dailytest.present.resource.StringResources

class StringsResourcesImpl(
    private val resourcesProvider: ResourcesProvider
) : StringResources {
    override val homeTitle: String =
        resourcesProvider.getString(R.string.app_name)
    override val errorUnavailableTitle: String =
        resourcesProvider.getString(R.string.error_unavailable_title)
    override val errorUnavailableMessage: String =
        resourcesProvider.getString(R.string.error_unavailable_message)
    override val errorActionRetry: String =
        resourcesProvider.getString(R.string.error_action_retry)
    override fun whenDateMinutes(minutes: String): String =
        resourcesProvider.getString(R.string.when_date_minutes, minutes)
    override fun whenDateHours(hours: String): String =
        resourcesProvider.getString(R.string.when_date_hours, hours)
}
