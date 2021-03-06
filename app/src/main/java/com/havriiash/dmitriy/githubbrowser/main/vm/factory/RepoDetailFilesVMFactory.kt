package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailFilesViewModel
import javax.inject.Inject
import javax.inject.Named

class RepoDetailFilesVMFactory
@Inject constructor(
        private val model: RepoRepository,
        private val userName: String,

        @Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)
        private val repoName: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoDetailFilesViewModel(model, userName, repoName) as T
    }
}