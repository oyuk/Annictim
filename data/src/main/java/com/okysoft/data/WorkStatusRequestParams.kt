package com.okysoft.data

data class WorkStatusRequestParams(
    private val workId: Int,
    private val kind: WatchKind
) {

    fun toParams(): Map<String, String> {
        return mapOf(Pair("work_id", workId.toString()), Pair("kind", kind.toString()))
    }

}