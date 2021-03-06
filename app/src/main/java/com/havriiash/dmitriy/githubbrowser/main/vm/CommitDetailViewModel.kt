package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class CommitDetailViewModel
@Inject constructor(
        model: RepoRepository,
        private val userName: String,
        private val repoName: String,
        private val shaId: String
) : BaseViewModel<RepoRepository>(model) {

    val commitObservable: MutableLiveData<RemoteResource<Commit>> = MutableLiveData()

    fun getCommitInfo() {
        disposables.add(
                model.getCommitInfo(userName, repoName, shaId)
                        .doOnSubscribe { commitObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { commit -> commitObservable.value = RemoteResource.success(commit) },
                                { throwable -> commitObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }
}