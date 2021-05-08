package simple.dms.myapplication.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PointList {

    @SerializedName("point_history")
    private List<Point> points = null;


    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
