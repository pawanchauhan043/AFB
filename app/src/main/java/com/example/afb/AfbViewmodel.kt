package com.example.afb


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.afb.utils.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AfbViewmodel @Inject constructor(val afbRepo: AfbRepo) : ViewModel() {

    fun getMemes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = afbRepo.getMemes()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}