package simple.dms.myapplication.ui.goodPoints;

import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.PointModel;
import simple.dms.myapplication.model.callback.LoadPointListener;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.data.UserToken;

public class GoodPresenter implements GoodContract.Presenter{

    private GoodContract.View view;
    private PointModel pointModel;
    private LoginModel loginModel;

    public GoodPresenter(GoodContract.View view) {
        this.view = view;
        pointModel = new PointModel();
        loginModel = new LoginModel();
    }

    @Override
    public void getPoints(String token) {
        pointModel.getPointHistory(token, new LoadPointListener() {
            @Override
            public void loadPoint(PointList points) {
                view.setPoints(points);
            }

            @Override
            public void wrongToken() {
                view.tokenError();
            }
        });
    }

    @Override
    public void refreshToken(String user) {
        loginModel.getNewAccessToken(user, new LoadTokenListener() {
            @Override
            public void loadToken(UserToken userToken) {
                view.retrySetPoints(userToken);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
