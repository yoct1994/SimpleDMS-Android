package simple.dms.myapplication.ui.main;

import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.data.UserToken;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view;
    LoginModel loginModel;
    LoadTokenListener loadTokenListener = new LoadTokenListener() {
        @Override
        public void loadToken(UserToken userToken) {
            view.setToken(userToken);
        }

        @Override
        public void onFail() {

        }
    };

    public MainPresenter(MainContract.View view) {
        this.view = view;
        loginModel = new LoginModel();
    }

    @Override
    public void getNewToken(String user) {
        loginModel.getNewAccessToken(user, loadTokenListener);
    }
}
