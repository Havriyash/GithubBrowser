package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(
        model: UserRepository,
        private val preferences: GithubBrowserPreferences
) : BaseViewModel<UserRepository>(model) {

    val userObserver: MutableLiveData<RemoteResource<User>> = MutableLiveData()

    fun getUserInfo() {
        disposables.add(
                model.getUserInfo(preferences.accessToken!!)
                        .doOnSubscribe { userObserver.value = RemoteResource.loading() }
                        .doAfterSuccess { user -> preferences.loggedUser = user }
                        .subscribe(
                                { user -> userObserver.value = RemoteResource.success(user) },
                                { throwable -> userObserver.value = RemoteResource.error(throwable) }
                        )
        )
    }

}