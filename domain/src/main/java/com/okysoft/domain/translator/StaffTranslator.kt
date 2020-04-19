package com.okysoft.domain.translator

import com.okysoft.infra.response.StaffResponse
import com.okysoft.domain.model.Staff

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