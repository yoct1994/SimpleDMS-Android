package simple.dms.myapplication.model;

import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.data.Login;
import simple.dms.myapplication.model.data.UserToken;
import simple.dms.myapplication.model.service.TokenService;

public class LoginModel {

    private final TokenService tokenService;

    public LoginModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://211.35.225.252:4001/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tokenService = retrofit.create(TokenService.class);
    }

    public void login(Login login, LoadTokenListener loadTokenListener) {
        Call<UserToken> call = tokenService.getToken(login);
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
                Integer code = response.code();
                Log.d("Login Http", code.toString());
                if(!response.isSuccessful()) {
                    loadTokenListener.onFail();
                    return;
                }
                loadTokenListener.loadToken(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {

            }
        });
    }

    public void getNewAccessToken(String user, LoadTokenListener loadTokenListener) {
        tokenService.reissuance(user).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                if(response.isSuccessful()) {
                    loadTokenListener.loadToken(response.body());
                }else {
                    loadTokenListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {

            }
        });
    }

}
