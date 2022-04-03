package com.xavier.pouyadoux.prog1.api.Api;

import com.xavier.pouyadoux.prog1.api.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Apiclient {

        @GET("todos")
        Call<List<Post>>getPost();


}
