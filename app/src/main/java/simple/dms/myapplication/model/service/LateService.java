package simple.dms.myapplication.model.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import simple.dms.myapplication.model.data.LateSeat;
import simple.dms.myapplication.model.data.PostLate;

public interface LateService {
    @GET("extensioninfo")
    Call<List<LateSeat>> getLateInfo(@Query("classNum") String classNum);

    @POST("extension")
    Call<Void> postLate(
            @Query("name") String name,
            @Body PostLate postLate
            );

    @DELETE("extensioncancel")
    Call<Void> cancelLate(@Query("name") String name);

    @GET("extensioninfo")
    Call<Boolean> getMyInfo(@Query("name") String name);
}
