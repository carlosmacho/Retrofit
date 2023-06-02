package ipvc.estg.retrofit.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EndPoints {
    @GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/comments/")
    fun getComments(): Call<List<Comment>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @GET("/comments/{id}")
    fun getCommentById(@Path("id") id: Int): Call<Comment>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") title: String?): Call<OutputPost>
}