package com.anushka.retrodemo1.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

//  @GET("/albums/{id}")
//  suspend fun getAlbum(@Path(value = "id")albumId:Int): Album

  /**
   * Para tratamento do retorno é melhor retornar um response.
   */
  @GET("/albums/{id}")
  suspend fun getAlbumResponse(@Path(value = "id") albumId:Int): Response<Album>

}