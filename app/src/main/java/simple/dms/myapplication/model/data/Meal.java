package simple.dms.myapplication.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meal {

    @SerializedName("breakfast")
    private List<String> breakfast = null;
    @SerializedName("dinner")
    private List<String> dinner = null;
    @SerializedName("lunch")
    private List<String> lunch = null;

    public List<String> getBreakfast() {
        return breakfast;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }
}
