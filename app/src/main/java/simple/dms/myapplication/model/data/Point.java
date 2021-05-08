package simple.dms.myapplication.model.data;

import com.google.gson.annotations.SerializedName;

public class Point {

    @SerializedName("date")
    private String date;

    @SerializedName("point")
    private int point;

    @SerializedName("pointType")
    private boolean pointType;

    @SerializedName("reason")
    private String reason;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isPointType() {
        return pointType;
    }

    public void setPointType(boolean pointType) {
        this.pointType = pointType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
