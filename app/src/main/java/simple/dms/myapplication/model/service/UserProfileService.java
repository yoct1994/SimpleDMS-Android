package simple.dms.myapplication.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import simple.dms.myapplication.model.data.UserProfile;

public interface UserProfileService {
    @GET("basic")
    Call<UserProfile> getUserProfile(@Header("Authorization")String token);
}
