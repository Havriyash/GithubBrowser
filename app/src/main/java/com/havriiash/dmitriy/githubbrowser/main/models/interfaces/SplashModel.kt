package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import com.havriiash.dmitriy.githubbrowser.data.repositories.IRepository
import io.reactivex.Single

interface SplashModel : IRepository {
    fun authorize(code: String): Single<AuthResponse>
}