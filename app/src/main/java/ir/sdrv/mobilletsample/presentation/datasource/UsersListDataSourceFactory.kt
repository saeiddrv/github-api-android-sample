package ir.sdrv.mobilletsample.presentation.datasource

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData
import ir.sdrv.mobilletsample.data.remote.api.models.GithubUserModel
import ir.sdrv.mobilletsample.domain.api.GithubApiClient

class UsersListDataSourceFactory(private val githubApiClient: GithubApiClient): DataSource.Factory<Int, GithubUserModel>() {

    val liveData: MutableLiveData<UsersListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, GithubUserModel> {
        val usersListDataSource = UsersListDataSource(githubApiClient)
        liveData.postValue(usersListDataSource)
        return usersListDataSource
    }
}