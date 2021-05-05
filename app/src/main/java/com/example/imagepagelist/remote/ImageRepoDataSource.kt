package com.example.imagepagelist.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagepagelist.model.Photo
import javax.inject.Inject


class ImageRepoDataSource @Inject constructor(private val apiService: ImageApi) : PagingSource<Int, Photo>() {

    private val TAG : String = ImageRepoDataSource::class.java.simpleName;

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        try {
            val currentLoadingPageKey = params.key ?: 1

            val response = apiService.getImageList(currentLoadingPageKey)
            val responseData = mutableListOf<Photo>()
            val photos = response.photos.photo

            photos?.forEach {
                responseData.add(it)
            }

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                    data = responseData,
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            Log.d(TAG,e?.message!!)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state?.anchorPosition
    }


}
