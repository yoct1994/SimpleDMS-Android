package simple.dms.myapplication.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.dms.myapplication.model.callback.LoadUserInfoListener;
import simple.dms.myapplication.model.data.UserProfile;
import simple.dms.myapplication.model.service.UserProfileService;

public class UserProfileModel {

    private final UserProfileService userProfileService;

    public UserProfileModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dsm-dms.com/info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userProfileService = retrofit.create(UserProfileService.class);
    }

   public void getUserProfile(String token, LoadUserInfoListener loadUserInfoListener) {
       Call<UserProfile> call = userProfileService.getUserProfile(token);
       call.enqueue(new Callback<UserProfile>() {
           @Override
           public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
               Integer code = response.code();
               Log.d("Profile Http", code.toString());
               if(response.code() == 403) {
                   loadUserInfoListener.wrongToken();
                   return;
               }
               if(response.isSuccessful()) {
                   loadUserInfoListener.loadUserProfile(response.body());
               }
           }

           @Override
           public void onFailure(Call<UserProfile> call, Throwable t) {

           }
       });
   }
}
