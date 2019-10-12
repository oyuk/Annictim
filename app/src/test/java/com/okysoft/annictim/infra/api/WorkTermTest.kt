package com.okysoft.annictim.infra.api

import com.google.common.truth.Truth.assertThat
import com.okysoft.data.WorkTerm
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.util.*

@PrepareForTest(WorkTerm::class, Calendar::class)
@RunWith(PowerMockRunner::class)
class WorkTermTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {

        }
    }

    @Before
    fun setUp() {
        val cal = Calendar.getInstance()
        cal.set(2018, 1, 26, 10, 10, 10)
        PowerMockito.mockStatic(Calendar::class.java)
        PowerMockito.`when`(Calendar.getInstance()).thenReturn(cal)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun current() {
        val workTerm = WorkTerm.Current
        assertThat(workTerm.term()).matches("2018-winter")
    }

    @Test
    fun next() {
        val workTerm = WorkTerm.Next
        assertThat(workTerm.term()).matches("2018-spring")
    }

    @Test
    fun previous() {
        val workTerm = WorkTerm.Previous
        assertThat(workTerm.term()).matches("2017-autumn")
    }

    @Test
    fun nextYear() {
//        TODO(あとでやるお)
//        val workTerm = WorkTerm.Next
//        assertThat(workTerm.term()).matches("2019-winter")
    }

}