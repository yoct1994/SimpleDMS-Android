package simple.dms.myapplication.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import simple.dms.myapplication.model.callback.LoadPointListener;
import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.service.PointService;

public class PointModel {

    private final PointService pointService;

    public PointModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dsm-dms.com/info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pointService = retrofit.create(PointService.class);
    }

    public void getPointHistory(String token, LoadPointListener loadPointListener) {
        Call<PointList> call = pointService.getPointHistory(token);
        call.enqueue(new Callback<PointList>() {
            @Override
            public void onResponse(Call<PointList> call, Response<PointList> response) {
                if(response.code() == 403) {
                    loadPointListener.wrongToken();
                    return;
                }
                if(response.isSuccessful()) {
                    loadPointListener.loadPoint(response.body());
                }
            }

            @Override
            public void onFailure(Call<PointList> call, Throwable t) {

            }
        });
    }
}
