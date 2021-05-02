package com.okysoft.domain.model

import android.os.Parcelable
import com.okysoft.infra.type.Media
import com.okysoft.infra.type.SeasonName
import com.okysoft.infra.type.StatusState
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkDetail(
    val id: String,
    val annictId: Int,
    val title: String,
    val media: Media,
    val twitterUsername: String?,
    val twitterHashtag: String?,
    val wikipediaUrl: String?,
    val officialSiteUrl: String?,
    val seasonName: SeasonName?,
    val seasonYear: Int?,
    val episodesCount: Int,
    val watchersCount: Int,
    val reviewsCount: Int,
    val noEpisodes: Boolean,
    val imageUrl: String,
    val viewerStatusState: StatusState?,
    val casts: List<Cast>,
    val episodes: List<Episode>,
    val staffs: List<Staff>,
): Parcelable {

    companion object {

        fun default(): WorkDetail {
            return WorkDetail(
                id = "",
                annictId = 0,
                title = "",
                media = Media.UNKNOWN__,
                episodesCount = 0,
                watchersCount = 0,
                reviewsCount = 0,
                noEpisodes = false,
                imageUrl = "",
                casts = emptyList(),
                episodes = emptyList(),
                staffs = emptyList(),
                twitterHashtag = null,
                twitterUsername = null,
                wikipediaUrl = null,
                officialSiteUrl = null,
                seasonName = null,
                seasonYear = null,
                viewerStatusState = null
            )
        }
    }

}