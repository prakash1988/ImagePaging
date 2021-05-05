package com.example.imagepagelist.remote

import androidx.paging.PagingData
import com.example.imagepagelist.model.Photo
import kotlinx.coroutines.flow.Flow

interface ImageRepo {
    fun getAllImages() : Flow<PagingData<Photo>>
}