package simple.dms.myapplication.model;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.dms.myapplication.model.callback.LoadLateListener;
import simple.dms.myapplication.model.callback.LoadMyLateInfoListener;
import simple.dms.myapplication.model.callback.PostLateListener;
import simple.dms.myapplication.model.data.LateSeat;
import simple.dms.myapplication.model.data.PostLate;
import simple.dms.myapplication.model.data.RequestLate;
import simple.dms.myapplication.model.service.LateService;

public class LateModel {

    private final LateService lateService;

    public LateModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://211.35.225.252:4001/api/")
                .build();
        lateService = retrofit.create(LateService.class);
    }

    public void getLate(String classNum, LoadLateListener loadLateListener) {
        Call<List<LateSeat>> call = lateService.getLateInfo(classNum);
        call.enqueue(new Callback<List<LateSeat>>() {
            @Override
            public void onResponse(Call<List<LateSeat>> call, Response<List<LateSeat>> response) {
                Log.d("LateHttp", Integer.toString(response.code()));
                if(response.isSuccessful()) {
                    loadLateListener.lateInfo(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<LateSeat>> call, Throwable t) {

            }
        });
    }

    public void postLate(RequestLate requestLate, PostLateListener postLateListener) {
        Call<Void> call = lateService.postLate(requestLate.getName(),
                new PostLate(Integer.toString(requestLate.getClassNum()), Integer.toString(requestLate.getSeatNum())));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("postExten", Integer.toString(response.code()));
                Log.d("cls", requestLate.getClassNum().toString());
                Log.d("seat", requestLate.getSeatNum().toString());
                if(response.code() == 412) {
                    postLateListener.wrongName();
                }
                if(response.code() == 403) {
                    postLateListener.wrongName();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void cancelLate(String name, PostLateListener postLateListener) {
        Call<Void> call = lateService.cancelLate(name);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("extenCancel", Integer.toString(response.code()));

                if(response.code() == 412) {
                    postLateListener.wrongName();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getMyInfo(String name, LoadMyLateInfoListener loadMyLateInfoListener) {
        Call<Boolean> call = lateService.getMyInfo(name);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("MyInfo", Integer.toString(response.code()));
                Log.d("MyInfoBool", Boolean.toString(response.body()));
                if(response.code() == 412) {
                    loadMyLateInfoListener.wrongName();
                }
                if(response.isSuccessful()){
                    loadMyLateInfoListener.getMyInfo(response.body());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
