package ir.sdrv.mobilletsample.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class GetGithubUserResponseModel(
    @SerializedName("total_count") var totalCount: Long,
    @SerializedName("incomplete_results") var incompleteResults: Boolean,
    @SerializedName("items") var items: List<GithubUserModel>
)