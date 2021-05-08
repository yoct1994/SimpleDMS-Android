package simple.dms.myapplication.ui.main;

import simple.dms.myapplication.model.data.UserToken;

public class MainContract {

    public interface View {
        void setToken(UserToken userToken);
    }
    public interface Presenter {
        void getNewToken(String user);
    }
}
