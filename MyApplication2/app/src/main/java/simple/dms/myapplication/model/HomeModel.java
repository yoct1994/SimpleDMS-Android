package simple.dms.myapplication.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.dms.myapplication.model.callback.LoadHomeListener;
import simple.dms.myapplication.model.callback.PostHomeComingListener;
import simple.dms.myapplication.model.data.Home;
import simple.dms.myapplication.model.data.PostHomeComing;
import simple.dms.myapplication.model.service.HomeComingService;

public class HomeModel {

    private final HomeComingService homeComingService;

    public HomeModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://220.90.237.33:4001/api/")
                .build();
        homeComingService = retrofit.create(HomeComingService.class);
    }

    public void postHome(PostHomeComing postHomeComing, PostHomeComingListener postHomeComingListener) {
        Call<Void> call = homeComingService.postHomeComing(postHomeComing.getName(), postHomeComing.getValue());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("PostHome", Integer.toString(response.code()));
                Log.d("PostName", postHomeComing.getName());
                Log.d("PostVal", postHomeComing.getValue().toString());
                Log.d("homeErrorMsg", response.message());
                if(!response.isSuccessful()) {
                    if(response.code() == 412) {
                        postHomeComingListener.wrongName();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getHomeInfo(String name, LoadHomeListener loadHomeListener) {
        Call<Home> call = homeComingService.getHomeComingInfo(name);
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Log.d("getHome", Integer.toString(response.code()));
                Log.d("getValue", response.body().getHomecoming().toString());
                loadHomeListener.loadHomeInfo(response.body());
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

            }
        });
    }
}
