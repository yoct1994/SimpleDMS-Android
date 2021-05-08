package simple.dms.myapplication.model.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import simple.dms.myapplication.model.data.Login;
import simple.dms.myapplication.model.data.UserToken;

public interface TokenService {
    @POST("auth")
    Call<UserToken> getToken(@Body Login login);

    @GET("reissuance")
    Call<UserToken> reissuance(@Query("name")String name);
}
