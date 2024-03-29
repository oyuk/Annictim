package com.okysoft.domain.translator

import com.okysoft.domain.model.*
import com.okysoft.infra.fragment.Character
import com.okysoft.infra.fragment.Work

class WorkDetailTranslator: Translator<Work, WorkDetail> {

    private class CharacterTranslator {
        fun translate(c: Character): com.okysoft.domain.model.Character {
            return c.run {
                com.okysoft.domain.model.Character(
                    id = annictId,
                    name = name,
                )
            }
        }
    }

    private class PersonTranslator {
        fun translate(p: com.okysoft.infra.fragment.Cast.Person): Person {
            return p.run {
                Person(
                    id = annictId,
                    name = name,
                    nameEn = nameEn
                )
            }
        }
    }

    override fun translate(response: Work): WorkDetail {
        val charactorTranslator = CharacterTranslator()
        val personTranslator = PersonTranslator()
        val casts = response.casts?.nodes?.filterNotNull()?.let {
            it.map { l ->
                val c = l.fragments.cast
                return@map Cast(
                    id = c.annictId,
                    name = c.name,
                    annictId = c.annictId,
                    person = personTranslator.translate(c.person),
                    character = charactorTranslator.translate(c.character.fragments.character)
                )
            }
        }
        val episodes = response.episodes?.nodes?.filterNotNull()?.let {
            it.map { l ->
                val e = l.fragments.episode
                return@map Episode(
                    id = e.annictId,
                    title = e.title ?: "",
                    numberText = e.numberText ?: "",
                )
            }
        }
        val staffs = response.staffs?.nodes?.filterNotNull()?.let {
            it.map { l ->
                val e = l.fragments.staff
                return@map Staff(
                    id = e.annictId,
                    name = e.name,
                    roleOther = e.roleOther,
                    roleText = e.roleText
                )
            }
        }
        return response.run {
            return@run WorkDetail(
                id = id,
                annictId = annictId,
                title = title,
                media = media,
                twitterUsername = twitterUsername,
                twitterHashtag = twitterHashtag,
                wikipediaUrl = wikipediaUrl,
                officialSiteUrl = officialSiteUrl,
                seasonName = seasonName,
                seasonYear = seasonYear,
                episodesCount = episodesCount,
                watchersCount = watchersCount,
                reviewsCount = reviewsCount,
                noEpisodes = noEpisodes,
                imageUrl = image?.recommendedImageUrl ?: "",
                viewerStatusState = viewerStatusState,
                casts = casts ?: emptyList(),
                episodes = episodes ?: emptyList(),
                staffs = staffs ?: emptyList()
            )
        }
    }

}