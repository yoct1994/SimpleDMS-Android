package simple.dms.myapplication.model.data;

public class RequestLate {
    private Integer classNum;
    private Integer seatNum;
    private String name;

    public RequestLate(String name, Integer classNum, Integer seatNum) {
        this.classNum = classNum;
        this.seatNum = seatNum;
        this.name = name;
    }


    public Integer getClassNum() {
        return classNum;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public String getName() {
        return name;
    }
}
