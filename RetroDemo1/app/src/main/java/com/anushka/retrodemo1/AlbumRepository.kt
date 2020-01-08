package com.anushka.retrodemo1

import com.anushka.retrodemo1.retrofit.RetrofitInstance

class AlbumRepository {

    private val albumService = RetrofitInstance.albumService

    suspend fun getAlbum(id: Int) = albumService.getAlbumResponse(id)
}