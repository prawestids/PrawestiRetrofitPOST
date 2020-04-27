package com.example.prawestiretrofitpost;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("mahasiswa")
    Call<List<Post>> getPost();

    @GET("mahasiswa")
    Call<List<Post>> getPost(@Query("id_siswa") String id_siswa);

    @POST("mahasiswa")
    Call<ResponseBody> createPost(@Body Post post);

    @PUT("mahasiswa")
    Call<ResponseBody> editPost(@Body Post post);

    @DELETE("mahasiswa/{id_siswa")
    Call<ResponseBody> deletePost(
            @Path("id_siswa") String id_siswa
    );
}
