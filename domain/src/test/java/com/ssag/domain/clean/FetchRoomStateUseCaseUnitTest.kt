package com.ssag.domain.clean

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ssag.domain.clean.entity.CleanStateEntity
import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.repository.CleanRepository
import com.ssag.domain.clean.usecase.FetchRoomStateUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRoomStateUseCaseUnitTest {

    private val cleanRepository = mock<CleanRepository>()

    private val fetchRoomStateUseCase = FetchRoomStateUseCase(cleanRepository)

    @Test
    fun testFetchRoomStateUseCase() {
        val roomStateEntity = RoomStateEntity(
            lightIsComplete = true,
            plugIsComplete = true,
            shoesAreComplete = true,
            studentA = RoomStateEntity.StudentEntity(
                3214,
                "신희원",
                CleanStateEntity(
                    beddingIsClean = true,
                    clotheIsClean = true,
                    personalPlaceIsClean = true
                )
            ),
            studentB = RoomStateEntity.StudentEntity(
                3318,
                "조호원",
                CleanStateEntity(
                    beddingIsClean = true,
                    clotheIsClean = true,
                    personalPlaceIsClean = true
                )
            ),
            studentC = RoomStateEntity.StudentEntity(
                3202,
                "김재원",
                CleanStateEntity(
                    beddingIsClean = true,
                    clotheIsClean = true,
                    personalPlaceIsClean = true
                )
            )
        )

        whenever(cleanRepository.fetchRoomState(306)).thenReturn(
            roomStateEntity
        )

        runBlocking {
            val result = fetchRoomStateUseCase.execute(306)
            assertEquals(result, roomStateEntity)
        }
    }
}