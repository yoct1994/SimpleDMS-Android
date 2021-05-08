package simple.dms.myapplication.ui.badPoints;

import android.util.Log;

import java.util.List;

import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.PointModel;
import simple.dms.myapplication.model.callback.LoadPointListener;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.data.Point;
import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.data.UserToken;

public class BadPresenter implements BadContract.Presenter{

    private BadContract.View view;
    private PointModel pointModel;
    private LoginModel loginModel;

    public BadPresenter(BadContract.View view) {
        this.view = view;
        loginModel = new LoginModel();
        pointModel = new PointModel();
    }

    @Override
    public void getPoints(String token) {
        pointModel.getPointHistory(token, new LoadPointListener() {
            @Override
            public void loadPoint(PointList points) {
                view.setBadPoints(points);
            }

            @Override
            public void wrongToken() {
                view.tokenError();
            }
        });
    }

    @Override
    public void refreshToken(String token) {
        loginModel.getNewAccessToken(token, new LoadTokenListener() {
            @Override
            public void loadToken(UserToken userToken) {
                view.retrySetBadPoints(userToken);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
