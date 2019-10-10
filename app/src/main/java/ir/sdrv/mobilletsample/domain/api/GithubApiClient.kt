package ir.sdrv.mobilletsample.domain.api

import ir.sdrv.mobilletsample.data.remote.api.base.Resource
import ir.sdrv.mobilletsample.data.remote.api.models.*

interface GithubApiClient {

    suspend fun getUsersList(page: Int, pageSize: Int): Resource<GetGithubUserResponseModel>

    suspend fun getUserInfo(username: String): Resource<GithubUserModel>
}