package ir.sdrv.mobilletsample.domain.api

import ir.sdrv.mobilletsample.data.remote.api.GithubApi
import ir.sdrv.mobilletsample.data.remote.api.base.Resource
import ir.sdrv.mobilletsample.data.remote.api.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubApiClientImpl(private val githubApi: GithubApi): GithubApiClient {

    override suspend fun getUsersList(page: Int, pageSize: Int): Resource<GetGithubUserResponseModel> = withContext(Dispatchers.IO) {
        try {
            val response = githubApi.getUsersList(page, pageSize)
            if (response.isSuccessful) {
                Resource.success(response.body())

            } else {
                Resource.error(response.message())
            }
        } catch (ex: Throwable) {
            Resource.error<GetGithubUserResponseModel>("${ex.message}")
        }
   }

    override suspend fun getUserInfo(username: String): Resource<GithubUserModel> = withContext(Dispatchers.IO) {
        try {
            val response = githubApi.getUserInfo(username)
            if (response.isSuccessful) {
                Resource.success(response.body())

            } else {
                Resource.error(response.message())
            }
        } catch (ex: Throwable) {
            Resource.error<GithubUserModel>("${ex.message}")
        }
    }
}