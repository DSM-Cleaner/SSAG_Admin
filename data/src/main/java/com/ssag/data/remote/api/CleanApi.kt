package com.ssag.data.remote.api

import com.ssag.data.remote.response.FetchRoomStateResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CleanApi {

    @GET("cleaning/{roomId}")
    suspend fun fetchRoomState(
        @Path("roomId") roomId: Int
    ): FetchRoomStateResponse
}