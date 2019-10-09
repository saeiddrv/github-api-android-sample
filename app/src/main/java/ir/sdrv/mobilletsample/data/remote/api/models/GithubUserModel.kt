package ir.sdrv.mobilletsample.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class GithubUserModel(
    @SerializedName("id") var id: Long,
    @SerializedName("login") var login: String,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("avatar_url") var avatarUrl: String,
    @SerializedName("gravatar_id") var gravatarId: String,
    @SerializedName("url") var url: String,
    @SerializedName("html_url") var htmlUrl: String,
    @SerializedName("followers_url") var followersUrl: String,
    @SerializedName("following_url") var followingUrl: String,
    @SerializedName("gists_url") var gistsUrl: String,
    @SerializedName("starred_url") var starredUrl: String,
    @SerializedName("subscriptions_url") var subscriptionsUrl: String,
    @SerializedName("organizations_url") var organizationsUrl: String,
    @SerializedName("repos_url") var reposUrl: String,
    @SerializedName("events_url") var eventsUrl: String,
    @SerializedName("received_events_url") var receivedEventsUrl: String,
    @SerializedName("type") var type: String,
    @SerializedName("site_admin") var siteAdmin: Boolean,
    @SerializedName("score") var score: Float
)