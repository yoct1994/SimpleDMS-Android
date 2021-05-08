package simple.dms.myapplication.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import simple.dms.myapplication.model.data.PointList;

public interface PointService {
    @GET("point")
    Call<PointList> getPointHistory(
            @Header("Authorization")String token
    );
}
