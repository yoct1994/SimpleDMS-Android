package simple.dms.myapplication.ui.login;

import simple.dms.myapplication.model.data.Login;
import simple.dms.myapplication.model.data.UserToken;

public class LoginContract {
    public interface View {
        void loginFail();
        void setToken(UserToken token);
    }

    public interface Presenter {
        void login(Login login);
    }
}
