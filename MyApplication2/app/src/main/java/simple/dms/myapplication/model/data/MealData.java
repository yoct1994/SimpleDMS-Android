package simple.dms.myapplication.model.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MealData {

    @SerializedName(value = "yyyy-mm-dd")
    private Meal mealData;

    public Meal getMealData() {
        return mealData;
    }

    public void setMealData(Meal mealData) {
        this.mealData = mealData;
    }
}
