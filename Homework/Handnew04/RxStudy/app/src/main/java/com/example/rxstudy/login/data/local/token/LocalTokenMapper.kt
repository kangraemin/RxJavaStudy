package com.example.rxstudy.login.data.local.token

import com.example.rxstudy.login.data.remote.login.LoginItem

object LocalTokenMapper {
   fun mappingRemoteDataToLocal(loginItem: LoginItem): LocalTokenItem {
      return LocalTokenItem(
         accessToken = loginItem.access,
         refreshToken = loginItem.refresh
      )
   }
}