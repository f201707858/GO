package omdb

import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray


class Omdb_Values {
        var title: String? = null
    var posterUrl: String? = null

    companion object {
        fun fromJson(jsonObject: JSONObject): Omdb_Values? {
            var omdb = Omdb_Values()
            try {
                omdb.title = jsonObject.getString("Title")
                omdb.posterUrl = jsonObject.getString("Poster")

            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }
            return omdb
        }
    }


    fun get_title(): String? {
        return title
    }

    fun get_PosterUrl(): String? {
        return posterUrl
    }
}