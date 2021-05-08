package simple.dms.myapplication.ui.goodPoints;

import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.data.UserToken;

public class GoodContract {
    interface View {
        void setPoints(PointList points);
        void retrySetPoints(UserToken userToken);
        void tokenError();
    }
    interface Presenter {
        void getPoints(String token);
        void refreshToken(String user);
    }
}
