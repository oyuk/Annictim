package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName


data class EpisodeResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("number") val number: Any,
    @SerializedName("number_text") val numberText: String,
    @SerializedName("sort_number") val sortNumber: Int,
    @SerializedName("title") val title: String,
    @SerializedName("records_count") val recordsCount: Int,
    @SerializedName("record_comments_count") val recordCommentsCount: Int,
    @SerializedName("work") val work: Work?
) {

    val fullTitle: String
       get() = "$numberText $title"

    data class Work(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("title_kana") val titleKana: String,
        @SerializedName("media") val media: String,
        @SerializedName("media_text") val mediaText: String,
        @SerializedName("season_name") val seasonName: String,
        @SerializedName("season_name_text") val seasonNameText: String,
        @SerializedName("released_on") val releasedOn: String,
        @SerializedName("released_on_about") val releasedOnAbout: String,
        @SerializedName("official_site_url") val officialSiteUrl: String,
        @SerializedName("wikipedia_url") val wikipediaUrl: String,
        @SerializedName("twitter_username") val twitterUsername: String,
        @SerializedName("twitter_hashtag") val twitterHashtag: String,
        @SerializedName("episodes_count") val episodesCount: Int,
        @SerializedName("watchers_count") val watchersCount: Int
    )
}