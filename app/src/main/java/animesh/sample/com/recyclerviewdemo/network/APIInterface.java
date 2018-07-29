package animesh.sample.com.recyclerviewdemo.network;

import animesh.sample.com.recyclerviewdemo.model.UserListResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("api/users")
    Call<UserListResp> getProjectList(@Query("page") String pageNumber);

}
