package com.example.appcheck.FakeDagger

import com.example.appcheck.Network.AppChecker

class InitializeFakeInjections {

    fun getAppChecker(): AppChecker {
       return AppChecker()
    }
}