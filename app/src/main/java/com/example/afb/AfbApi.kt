package com.example.afb

import com.example.afb.data.AfbModel
import retrofit2.http.GET

interface AfbApi {

    @GET("get_memes")
    suspend fun getMemes(): AfbModel
}