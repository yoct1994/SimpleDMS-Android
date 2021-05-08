package simple.dms.myapplication.model.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import simple.dms.myapplication.model.data.Home;

public interface HomeComingService {
    @FormUrlEncoded
    @POST("homecoming")
    Call<Void> postHomeComing(@Query(value = "name", encoded = true) String name,
                              @Field(value = "value", encoded = true) Integer value);
    @GET("homecominginfo")
    Call<Home> getHomeComingInfo(@Query("name") String name);
}
