package com.ssag.data.remote.api

import com.ssag.data.remote.request.PostRoomStateRequest
import com.ssag.data.remote.response.FetchRoomStateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CleanApi {

    @GET("cleaning/{roomId}/day/{day}")
    suspend fun fetchRoomState(
        @Path("roomId") roomId: Int,
        @Path("day") dayOfWeek: String
    ): FetchRoomStateResponse

    @POST("cleaning/check/{roomId}")
    suspend fun postCleanState(
        @Path("roomId") roomId: Int,
        @Body request: PostRoomStateRequest
    )
}