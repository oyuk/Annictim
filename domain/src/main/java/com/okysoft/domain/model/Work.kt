package com.okysoft.domain.model

import android.os.Parcelable
import com.okysoft.common.Diffable
import com.okysoft.data.WatchKind
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Work(
    val id: Int,
    val title: String,
    val media: String,
    val twitterUsername: String,
    val twitterHashtag: String,
    val wikipediaUrl: String,
    val officialSiteUrl: String,
    val mediaText: String,
    val seasonNameText: String,
    val episodesCount: Int,
    val watchersCount: Int,
    val reviewsCount: Int,
    val noEpisodes: Boolean,
    val imageUrl: String,
    val watchKind: WatchKind
): Parcelable, Diffable {

    companion object {

        fun default(): Work {
            return Work(
                id = 0,
                title = "",
//                titleKana = null,
                media = "",
                mediaText = "",
//                seasonName = null,
                seasonNameText = "",
//                releasedOn = null,
//                releasedOnAbout = null,
                officialSiteUrl = "",
                wikipediaUrl = "",
                twitterUsername = "",
                twitterHashtag = "",
                watchKind = WatchKind.no_select,
//                malAnimeId = null,
//                images = WorkResponse.Images("",
//                    WorkResponse.Facebook(""),
//                    WorkResponse.Twitter("", "", "", "", "")),
                episodesCount = 0,
                watchersCount = 0,
                reviewsCount = 0,
                noEpisodes = false,
                imageUrl = ""
//                status = WorkResponse.Status("no_select")
            )
        }
    }

    override fun isTheSame(other: com.okysoft.common.Diffable): Boolean {
        return id == (other as? Work)?.id
    }

}