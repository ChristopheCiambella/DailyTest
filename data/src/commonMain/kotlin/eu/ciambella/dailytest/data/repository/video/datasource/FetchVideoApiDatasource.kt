package eu.ciambella.dailytest.data.repository.video.datasource

import eu.ciambella.dailytest.data.api.DailymotionApi
import eu.ciambella.dailytest.data.api.response.VideosResponse
import eu.ciambella.dailytest.domain.exception.DataException
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

class FetchVideoApiDatasource(
    private val dailymotionApi: DailymotionApi,
) {

    companion object {
        private const val REQUEST_ERROR_MESSAGE = "FetchVideoApiDatasource : %s"
        private const val VIDEOS_QUERY_FIELDS =
            "id,title,description,created_time,thumbnail_url"
    }

    suspend fun fetchVideos(page: Int): VideosResponse {
        val response = dailymotionApi.getVideos(page, VIDEOS_QUERY_FIELDS)
        if (response.status == HttpStatusCode.OK) {
            return requireNotNull(response.body())
        } else {
            throw DataException(REQUEST_ERROR_MESSAGE.format(response.status))
        }
    }

}
