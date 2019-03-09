package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.infra.api.model.response.ProgramResponse

class ProgramTranslator: Translator<ProgramResponse, Program> {

    override fun translate(response: ProgramResponse): Program {
        return Program(
            id = response.id,
            episode = response.episode,
            startedAt = response.startedAt,
            workResponse = response.workResponse,
            channel = Program.Channel(
                id = response.channel.id,
                name = response.channel.name
            )
        )
    }

}