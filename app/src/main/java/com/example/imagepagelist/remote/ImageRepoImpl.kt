package com.example.imagepagelist.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imagepagelist.model.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class ImageRepoImpl @Inject constructor(private val service: ImageApi) : ImageRepo{

    companion object{
        const val BASE_URL = "https://api.flickr.com/"
        const val pageURL = "https://reqres.in/api/users/?page="
    }



    override fun getAllImages(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { ImageRepoDataSource(service) }
        ).flow
    }


}
