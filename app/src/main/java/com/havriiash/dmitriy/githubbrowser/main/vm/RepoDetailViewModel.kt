package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class RepoDetailViewModel
@Inject constructor(
        model: RepoRepository,
        private val userName: String,
        private val repoName: String
) : BaseViewModel<RepoRepository>(model) {

    val userRepoInfo: MutableLiveData<RemoteResource<Repo>> = MutableLiveData()

    fun getUserRepo() {
        disposables.add(
                model.getRepoInfo(userName, repoName)
                        .doOnSubscribe { userRepoInfo.value = RemoteResource.loading() }
                        .subscribe(
                                { repo -> userRepoInfo.value = RemoteResource.success(repo) },
                                { throwable -> userRepoInfo.value = RemoteResource.error(throwable) }
                        )
        )
    }
}