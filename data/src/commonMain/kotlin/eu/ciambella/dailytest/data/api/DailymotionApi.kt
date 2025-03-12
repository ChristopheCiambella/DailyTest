package eu.ciambella.dailytest.data.api

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class DailymotionApi(
    private val dailymotionHttpClient: DailymotionHttpClient,
) {

    companion object {
        private const val VIDEOS_ENDPOINT = "videos"

        private const val QUERY_PAGE = "page"
        private const val QUERY_FIELDS = "fields"
    }

    suspend fun getVideos(
        page: Int,
        fields: String,
    ): HttpResponse = dailymotionHttpClient.httpClient.get(VIDEOS_ENDPOINT) {
        contentType(ContentType.Application.Json)
        url {
            parameters.append(QUERY_PAGE, page.toString())
            parameters.append(QUERY_FIELDS, fields)
        }
    }

}
