package simple.dms.myapplication.ui.badPoints;

import java.util.List;

import simple.dms.myapplication.model.data.Point;
import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.data.UserToken;

public class BadContract {
    public interface View {
        void setBadPoints(PointList points);
        void retrySetBadPoints(UserToken userToken);
        void tokenError();
    }
    public interface Presenter {
        void getPoints(String token);
        void refreshToken(String token);
    }
}
