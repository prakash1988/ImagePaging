package com.example.imagepagelist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagepagelist.adapter.ImageListAdapter
import com.example.imagepagelist.databinding.ImageListBinding
import com.example.imagepagelist.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : Fragment(){

    private val imageListViewModel : ImageListViewModel by viewModels()

    @Inject
    lateinit var imageListAdapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ImageListBinding.inflate(layoutInflater)
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.imageList.layoutManager = linearLayoutManager
        binding.imageList.adapter = imageListAdapter
        with(imageListViewModel){
            launchOnLifecycleScope {
                photosFlow.collectLatest {
                    Log.d("ImageList",""+it)
                    imageListAdapter.submitData(it)
                }
            }

        }
        return binding.root
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }
}