package com.okysoft.annictim

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class ApplicationStore {

    private val _accessToken = BehaviorRelay.create<String>()
    val accessToken: Observable<String> = _accessToken


}