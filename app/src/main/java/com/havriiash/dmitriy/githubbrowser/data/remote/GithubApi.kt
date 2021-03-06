package com.havriiash.dmitriy.githubbrowser.data.remote

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.*
import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import io.reactivex.Single
import retrofit2.http.*

interface GithubApi {

    companion object {
        const val BASE_URL: String = "https://api.github.com/"

        const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize?client_id=${Credentials.CLIENT_ID}&scope=${Credentials.PERMISSIONS}"

        private object Credentials {
            const val CLIENT_ID = "ccc423918b31b976026d"
            const val CLIENT_SECRET = "8f47833d3bd2b6b8d94f47b3be06903f78689e5d"

            const val PERMISSIONS = "repo,gist,user"
        }
    }

//    =============================================================================================
//    Auth queries

    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json")
    fun authorize(@Query("code") code: String,
                  @Query("client_id") clientId: String = Credentials.CLIENT_ID,
                  @Query("client_secret") clientSecret: String = Credentials.CLIENT_SECRET): Single<AuthResponse>
//    =============================================================================================
//    News queries

    @GET("/users/{user}/received_events")
    fun getNews(@Path("user") user: String,
                @Query("page") page: Int,
                @Query("per_page") count: Int,
                @Query("access_token") token: String): Single<List<News>>

//    =============================================================================================
//    Repository queries

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") userName: String,
                     @Query("page") page: Int,
                     @Query("per_page") count: Int,
                     @Query("access_token") token: String): Single<List<Repo>>

    @GET("/repos/{username}/{repo_name}")
    fun getUserRepo(@Path("username") userName: String,
                    @Path("repo_name") repoName: String,
                    @Query("access_token") token: String): Single<Repo>

    @GET("/repos/{username}/{repo_name}/contents/{deeper_path}")
    fun getRepoContents(@Path("username") userName: String,
                        @Path("repo_name") repoName: String,
                        @Path("deeper_path") deeperPath: String?,
                        @Query("access_token") token: String): Single<List<Repo.File>>

    @GET("/repos/{username}/{repo_name}/commits")
    fun getRepoCommits(@Path("username") userName: String,
                       @Path("repo_name") repoName: String,
                       @Query("page") page: Int,
                       @Query("per_page") count: Int,
                       @Query("access_token") token: String): Single<List<Commit>>

    @GET("/repos/{username}/{repo_name}/commits/{sha}")
    fun getRepoCommit(@Path("username") userName: String,
                      @Path("repo_name") repoName: String,
                      @Path("sha") sha: String,
                      @Query("access_token") token: String): Single<Commit>

    @GET("/repos/{username}/{repo_name}/events")
    fun getRepoEvents(@Path("username") userName: String,
                      @Path("repo_name") repoName: String,
                      @Query("page") page: Int,
                      @Query("per_page") count: Int,
                      @Query("access_token") token: String): Single<List<User.UserActivity>>

    @GET("/repos/{username}/{repo_name}/stargazers")
    fun getRepoStargazers(@Path("username") userName: String,
                          @Path("repo_name") repoName: String,
                          @Query("page") page: Int,
                          @Query("per_page") count: Int,
                          @Query("access_token") token: String): Single<List<Follower>>

    @GET("/repos/{username}/{repo_name}/contributors")
    fun getRepoContributors(@Path("username") userName: String,
                          @Path("repo_name") repoName: String,
                          @Query("page") page: Int,
                          @Query("per_page") count: Int,
                          @Query("access_token") token: String): Single<List<Follower>>

//    =============================================================================================
//    User queries

    @GET("/user")
    fun getUser(@Query("access_token") token: String): Single<User>

    @GET("/users/{username}")
    fun getUserByName(@Path("username") userName: String,
                      @Query("access_token") token: String): Single<User>

    @GET("/users/{username}/orgs")
    fun getUserOrganizations(@Path("username") userName: String,
                             @Query("access_token") token: String): Single<List<Organization>>

    @GET("/users/{username}/starred")
    fun getUserStarred(@Path("username") userName: String,
                       @Query("page") page: Int,
                       @Query("per_page") count: Int,
                       @Query("access_token") token: String): Single<List<User.Starred>>

    @GET("/users/{username}/events/public")
    fun getUserActivity(@Path("username") userName: String,
                        @Query("page") page: Int,
                        @Query("per_page") count: Int,
                        @Query("access_token") token: String): Single<List<User.UserActivity>>

//    =============================================================================================
//    Follows queries

    @GET("/users/{username}/followers")
    fun getFollowers(@Path("username") userName: String,
                     @Query("page") page: Int,
                     @Query("per_page") count: Int,
                     @Query("access_token") token: String): Single<List<Follower>>

    @GET("/users/{username}/following")
    fun getFollowing(@Path("username") userName: String,
                     @Query("page") page: Int,
                     @Query("per_page") count: Int,
                     @Query("access_token") token: String): Single<List<Follower>>

//    =============================================================================================
//    Gists queries

//    =============================================================================================
//    Search queries

//    =============================================================================================
}