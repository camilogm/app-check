package com.example.appcheck.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appcheck.FakeDagger.InitializeFakeInjections
import com.example.appcheck.Network.ApiClient.retrofitInstance
import com.example.appcheck.Network.ApiService
import com.example.appcheck.Network.AppChecker
import com.example.appcheck.R
import com.example.appcheck.databinding.FragmentGalleryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private var _appChecker: AppChecker = InitializeFakeInjections().getAppChecker()


    private val binding get() = _binding!!

    private val apiService = retrofitInstance!!.create(ApiService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.getPhotosButton)

        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        button.setOnClickListener{
            getPhotos()
        }
    }

    private fun getPhotos() {
        println("calling")

        GlobalScope.launch(Dispatchers.IO) {
            val token = _appChecker.getToken()
            println("************")
            println(token)

            try {
                val response = apiService.getPhotos(token)
                val body = response?.execute()?.body()

                println(body?.url)

            } catch (e: Exception) {
                // Handle network failure on the main thread
                Log.e("my-app", e.toString())

            }
        }
    }

}