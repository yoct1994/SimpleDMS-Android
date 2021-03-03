package simple.dms.myapplication.ui.home;

import simple.dms.myapplication.model.data.Home;
import simple.dms.myapplication.model.data.PostHomeComing;

public class HomeContract {
    public interface View {
        void setHomeInfo(Home value);
        void wrongName();
    }
    public interface Presenter {
        void postHome(PostHomeComing postHomeComing);
        void getHomeInfo(String name);
    }
}
