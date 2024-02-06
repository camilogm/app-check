package com.example.appcheck.Network

import com.example.appcheck.Model.PhotosModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {
    @GET("/photos")
    fun getPhotos(@Header("X-Firebase-AppCheck") token: String): Call<PhotosModel?>?
}