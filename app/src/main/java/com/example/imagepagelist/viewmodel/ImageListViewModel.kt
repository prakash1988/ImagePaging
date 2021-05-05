package com.example.imagepagelist.viewmodel


import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imagepagelist.model.Photo
import com.example.imagepagelist.remote.ImageRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val imageRepoImpl: ImageRepoImpl): BaseViewModel(){

    private lateinit var _photosFlow: Flow<PagingData<Photo>>
    val photosFlow: Flow<PagingData<Photo>>
        get() = _photosFlow

    init {
        getAllUserList()
    }
    private fun getAllUserList() = launchPagingAsync({

        imageRepoImpl.getAllImages().cachedIn(viewModelScope)
    }, {
        _photosFlow = it
    })

}