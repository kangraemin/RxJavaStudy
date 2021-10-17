package com.example.rxjavalecture.exercise.data.local.token

import com.example.rxjavalecture.exercise.data.remote.login.LoginItem


object LocalTokenMapper {
    fun mappingRemoteDataToLocal(loginItem: LoginItem): LocalTokenItem {
        return LocalTokenItem(
            accessToken = loginItem.access,
            refreshToken = loginItem.refresh
        )
    }
}