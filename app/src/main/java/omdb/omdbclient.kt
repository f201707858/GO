package omdb

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams


class omdbclient(relative_url: String) {
    var Relative_Url = relative_url
    private val API_KEY = "15c7fb28"
    private val API_BASE_URL = "http://www.omdbapi.com/"
    private val client: AsyncHttpClient

    init {
        this.client = AsyncHttpClient()
    }

    private fun getApiUrl(relativeUrl: String): String {
        return API_BASE_URL + "?t=" + relativeUrl + "&"

    }

    fun getBoxOfficeMovies(handler: JsonHttpResponseHandler) {
        val url = getApiUrl(Relative_Url)
        val params = RequestParams("apikey", API_KEY)
        Log.d("A", (url + params).toString())
        client.get(url, params, handler)


    }

}