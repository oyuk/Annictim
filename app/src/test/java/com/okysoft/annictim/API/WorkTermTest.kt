package com.okysoft.annictim.API

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class WorkTermTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun current() {
        val clock = Clock.fixed(Instant.parse("2018-08-26T10:30:00.00Z"), ZoneId.systemDefault())
        
    }

}