package simple.dms.myapplication.model.data;

public class PostHomeComing {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostHomeComing(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public PostHomeComing() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
