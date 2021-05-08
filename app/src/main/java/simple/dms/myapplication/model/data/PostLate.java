package simple.dms.myapplication.model.data;

public class PostLate {
    private String classNum;
    private String seatNum;

    public PostLate(String classNum, String seatNum) {
        this.classNum = classNum;
        this.seatNum = seatNum;
    }

    public String getClassNum() {
        return classNum;
    }

    public String getSeatNum() {
        return seatNum;
    }
}
