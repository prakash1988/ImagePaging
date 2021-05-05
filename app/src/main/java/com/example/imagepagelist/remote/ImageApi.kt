package com.example.imagepagelist.remote

import com.example.imagepagelist.model.PhotosModel
import com.example.imagepagelist.model.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=1eb8a20b8167c81d0a49f25783ec5670&format=json&text=bank&nojsoncallback=1")
    suspend fun getImageList(@Query("page") page: Int) : PhotosModel
}