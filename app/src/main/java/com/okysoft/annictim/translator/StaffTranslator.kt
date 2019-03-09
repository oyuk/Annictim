package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.infra.api.model.response.StaffResponse

class StaffTranslator: Translator<StaffResponse, Staff> {

    override fun translate(response: StaffResponse): Staff {
        return Staff(
            id = response.id,
            name = response.name,
            roleOther = response.roleOther,
            roleText = response.roleText
        )
    }

}