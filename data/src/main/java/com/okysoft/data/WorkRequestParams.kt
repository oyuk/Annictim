package com.okysoft.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkRequestParams(
    val type: Type = Type.Works,
    val fields: Fields = Fields.All,
    val ids: List<Int> = listOf(),
    val season: String? = null,
    val title: String? = null,
    val status: String? = null,
    val page: Int = 1,
    val perPage: Int = 20,
    val sortId: String? = null,
    val sortSeason: String? = null,
    val sortWatchersCount: String = "desc"): Parcelable {

//    constructor(
//        type: Type = Type.Works,
//        fields: Fields = Fields.All,
//        ids: List<Int> = listOf(),
//        workTerm: WorkTerm = WorkTerm.Current,
//        title: String? = null,
//        status: String? = null,
//        page: Int = 1,
//        perPage: Int = 20,
//        sortId: String? = null,
//        sortSeason: String? = null,
//        sortWatchersCount:String = "desc"): this(
//        type,
//        fields,
//        ids,
//        workTerm.term(),
//        title,
//        status,
//        page,
//        perPage,
//        sortId,
//        sortSeason,
//        sortWatchersCount
//    )
    enum class Type {
        Works, Me
    }

    enum class Fields {
        All,
        Feed,
        Status;

        fun toParams(): String {
            return when (this) {
                All -> ""
                Feed -> "id,title,images,watchers_count,reviews_count,episodes_count,no_episodes,season_name_text,media_text,official_site_url,wikipedia_url,twitter_username,twitter_hashtag,status.kind"
                Status -> "status.kind"
            }
        }
    }

    fun toParams(): Map<String, String>
        = mutableMapOf<String, String>().apply {
        this["fields"] = fields.toParams()
        if (ids.isNotEmpty()) {
            this["filter_ids"] = ids.joinToString(separator = ",")
        }
        season?.let {
            this["filter_season"] = it
        }
        title?.let {
            this["filter_title"] = it
        }
        status?.let {
            this["filter_status"] = it
        }
        this["page"] = page.toString()
        this["per_page"] = perPage.toString()
        sortId?.let {
            this["sort_id"] = it
        }
        sortSeason?.let {
            this["sort_season"] = it
        }
        this["sort_watchers_count"] = sortWatchersCount
    }

    companion object {
        fun empty(): WorkRequestParams {
            return WorkRequestParams()
        }
    }

}
