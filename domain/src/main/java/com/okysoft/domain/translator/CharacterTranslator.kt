package com.okysoft.domain.translator

import com.okysoft.domain.model.Character
import com.okysoft.infra.response.CastResponse

class CharacterTranslator(private val seriesTranslator: SeriesTranslator): Translator<CastResponse.CharacterResponse, Character> {

    override fun translate(response: CastResponse.CharacterResponse): Character {
        return Character(
            age = response.age,
            ageEn = response.ageEn,
            birthday = response.birthday,
            birthdayEn = response.birthdayEn,
            bloodType = response.bloodType,
            bloodTypeEn = response.bloodTypeEn,
            description = response.description,
            descriptionEn = response.descriptionEn,
            descriptionSource = response.descriptionSource,
            descriptionSourceEn = response.descriptionSourceEn,
            favoriteCharactersCount = response.favoriteCharactersCount,
            height = response.height,
            heightEn = response.heightEn,
            id = response.id,
            kind = response.kind,
            name = response.name,
            nameEn = response.nameEn,
            nameKana = response.nameKana,
            nationality = response.nationality,
            nationalityEn = response.nationalityEn,
            nickname = response.nickname,
            nicknameEn = response.nicknameEn,
            occupation = response.occupation,
            occupationEn = response.occupationEn,
            series = response.series?.let { seriesTranslator.translate(it) },
            weight = response.weight,
            weightEn = response.weightEn
        )
    }
}