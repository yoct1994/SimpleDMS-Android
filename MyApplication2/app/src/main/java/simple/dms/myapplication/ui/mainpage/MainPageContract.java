package simple.dms.myapplication.ui.mainpage;

import simple.dms.myapplication.model.data.Meal;
import simple.dms.myapplication.model.data.MealData;
import simple.dms.myapplication.model.data.UserProfile;
import simple.dms.myapplication.model.data.UserToken;

public class MainPageContract {
    public interface View {
        void setMeals(Meal meals);
        void setUserProfile(UserProfile userProfile);
        void retryGetUserProfile(UserToken userToken);
        void retryGetMeals(UserToken userToken);
        void gotoLogin();
        void tokenError();
    }
    public interface Presenter {
        void getUserProfile(String token);
        void getMeal();
        void refreshToken(String user);
    }
}
