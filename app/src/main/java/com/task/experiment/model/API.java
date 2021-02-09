package com.task.experiment.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("repositories")
    Call<PostData> getData(
            @Query("q") String language
    );
}
