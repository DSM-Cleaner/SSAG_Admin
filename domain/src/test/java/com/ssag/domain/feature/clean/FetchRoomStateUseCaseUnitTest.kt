package com.ssag.domain.feature.clean

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.repository.CleanRepository
import com.ssag.domain.feature.clean.usecase.FetchRoomStateUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRoomStateUseCaseUnitTest {

    private val cleanRepository = mock<CleanRepository>()

    private val fetchRoomStateUseCase = FetchRoomStateUseCase(cleanRepository)

    @Test
    fun testFetchRoomStateUseCase() {
        val roomStateEntity = RoomStateEntity(
            lightIsNotComplete = true,
            plugIsNotComplete = true,
            shoesAreNotComplete = true,
            students = emptyList()
        )

        runBlocking {
            whenever(cleanRepository.fetchRoomState(306)).thenReturn(
                roomStateEntity
            )

            val result = fetchRoomStateUseCase.execute(306)
            assertEquals(result, roomStateEntity)
        }
    }
}