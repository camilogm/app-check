package com.example.appcheck.Network

import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AppChecker {

    suspend fun getAppCheckTokenSynchronously(): String {
        return suspendCoroutine { continuation ->
            Firebase.appCheck.getAppCheckToken(true)
                .addOnSuccessListener { appCheckToken ->
                    val token = appCheckToken.token
                    continuation.resume(token)
                }
                .addOnFailureListener { error ->

                    continuation.resumeWithException(error)
                }
        }
    }

    suspend fun getToken(): String{
        //make logic to know ttl

        return getAppCheckTokenSynchronously()
    }
}