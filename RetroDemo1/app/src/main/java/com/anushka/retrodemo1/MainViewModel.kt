package com.anushka.retrodemo1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import okhttp3.ResponseBody
import org.json.JSONObject


class MainViewModel: ViewModel() {

    private val albumRepository = AlbumRepository()

    /*val album = liveData {
        emit(albumRepository.getAlbum(1))
    }*/

    val album = liveData {
        val response = albumRepository.getAlbum(1)

        when (response.isSuccessful) {
            true -> emit(Result.sucess(response.body()!!))
            false -> emit(Result.error(getErrorMessage(response.errorBody()!!)))
        }
    }

    private fun getErrorMessage(error: ResponseBody): String {
        val exception = JSONObject(error.string())
        return exception.getJSONObject("error").getString("message")
    }
}