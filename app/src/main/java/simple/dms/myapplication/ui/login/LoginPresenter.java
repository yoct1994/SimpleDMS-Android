package simple.dms.myapplication.ui.login;

import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.data.Login;
import simple.dms.myapplication.model.data.UserToken;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;
    private LoginModel loginModel;

    private LoadTokenListener loadTokenListener = new LoadTokenListener() {
        @Override
        public void loadToken(UserToken userToken) {
            view.setToken(userToken);
        }

        @Override
        public void onFail() {
            view.loginFail();
        }
    };

    public LoginPresenter(LoginContract.View view) {
        loginModel = new LoginModel();
        this.view = view;
    }

    @Override
    public void login(Login login) {
        loginModel.login(login, loadTokenListener);
    }
}
