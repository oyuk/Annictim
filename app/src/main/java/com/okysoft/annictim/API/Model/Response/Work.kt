package com.okysoft.annictim.API.Model.Response

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Work(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @Nullable @SerializedName("title_kana") val titleKana: String,
        @Nullable @SerializedName("media") val media: String,
        @Nullable @SerializedName("media_text") val mediaText: String,
        @Nullable @SerializedName("season_name") val seasonName: String,
        @Nullable @SerializedName("season_name_text") val seasonNameText: String,
        @Nullable @SerializedName("released_on") val releasedOn: String,
        @Nullable @SerializedName("released_on_about") val releasedOnAbout: String,
        @Nullable @SerializedName("official_site_url") val officialSiteUrl: String,
        @Nullable @SerializedName("wikipedia_url") val wikipediaUrl: String,
        @Nullable @SerializedName("twitter_username") val twitterUsername: String,
        @Nullable @SerializedName("twitter_hashtag") val twitterHashtag: String,
        @Nullable @SerializedName("mal_anime_id") val malAnimeId: String,
        @SerializedName("images") val images: Images,
        @Nullable @SerializedName("episodes_count") val episodesCount: Int,
        @Nullable @SerializedName("watchers_count") val watchersCount: Int
): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelWork.CREATOR
    }

    @PaperParcel
    data class Images(
            @SerializedName("recommended_url") val recommendedUrl: String,
            @SerializedName("facebook") val facebook: Facebook,
            @SerializedName("twitter") val twitter: Twitter
    ): PaperParcelable {

        companion object {
            @JvmField val CREATOR = PaperParcelWork_Images.CREATOR
        }
    }

    @PaperParcel
    data class Facebook(
            @SerializedName("og_image_url") val ogImageUrl: String
    ): PaperParcelable {

        companion object {
            @JvmField val CREATOR = PaperParcelWork_Facebook.CREATOR
        }
    }

    @PaperParcel
    data class Twitter(
            @SerializedName("mini_avatar_url") val miniAvatarUrl: String,
            @SerializedName("normal_avatar_url") val normalAvatarUrl: String,
            @SerializedName("bigger_avatar_url") val biggerAvatarUrl: String,
            @SerializedName("original_avatar_url") val originalAvatarUrl: String,
            @SerializedName("image_url") val imageUrl: String
    ): PaperParcelable {

        companion object {
            @JvmField val CREATOR = PaperParcelWork_Twitter.CREATOR
        }
    }

}