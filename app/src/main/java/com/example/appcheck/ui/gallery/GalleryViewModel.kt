package com.example.appcheck.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import java.lang.Error

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    fun onButtonClicked() {
        Log.d("GalleryViewModel", "Button Clickedddd!") // Log the button click actio

    }

}