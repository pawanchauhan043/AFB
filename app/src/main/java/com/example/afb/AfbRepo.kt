package com.example.afb

import com.example.afb.data.Meme
import javax.inject.Inject

class AfbRepo @Inject constructor(val afbApi: AfbApi) {

    suspend fun getMemes(): List<Meme> {
        return afbApi.getMemes().data.memes
    }
}