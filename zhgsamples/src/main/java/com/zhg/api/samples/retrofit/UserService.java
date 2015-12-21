package com.zhg.api.samples.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by nyzhang on 2015/12/21.
 */
public interface UserService {
    @POST("/ZhgServer/user/{user}/repos")
    Call<List<Result>> listRepos(@Path("user") String user);
}
