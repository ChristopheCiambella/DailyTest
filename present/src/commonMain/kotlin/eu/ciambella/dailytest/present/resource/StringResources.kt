package eu.ciambella.dailytest.present.resource

interface StringResources {
    val homeTitle: String
    val errorUnavailableTitle: String
    val errorUnavailableMessage: String
    val errorActionRetry: String
    fun whenDateMinutes(minutes: String): String
    fun whenDateHours(hours: String): String
}
