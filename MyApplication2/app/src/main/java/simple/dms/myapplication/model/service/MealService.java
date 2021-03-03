package simple.dms.myapplication.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import simple.dms.myapplication.model.data.Meal;

public interface MealService {
    @GET("meal")
    Call<Meal> getMeal();
}
