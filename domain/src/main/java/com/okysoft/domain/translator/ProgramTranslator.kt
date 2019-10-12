package com.okysoft.domain.translator

import com.okysoft.data.ProgramResponse
import com.okysoft.domain.model.Program

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