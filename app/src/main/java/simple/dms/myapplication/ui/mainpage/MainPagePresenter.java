package simple.dms.myapplication.ui.mainpage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.MealModel;
import simple.dms.myapplication.model.UserProfileModel;
import simple.dms.myapplication.model.callback.LoadMealListener;
import simple.dms.myapplication.model.callback.LoadTokenListener;
import simple.dms.myapplication.model.callback.LoadUserInfoListener;
import simple.dms.myapplication.model.data.Meal;
import simple.dms.myapplication.model.data.MealData;
import simple.dms.myapplication.model.data.UserProfile;
import simple.dms.myapplication.model.data.UserToken;

public class MainPagePresenter implements MainPageContract.Presenter{

    private MainPageContract.View view;

    private MealModel mealModel;
    private UserProfileModel userProfileModel;
    private LoginModel loginModel;

    public MainPagePresenter(MainPageContract.View view) {
        this.view = view;
        mealModel = new MealModel();
        userProfileModel = new UserProfileModel();
        loginModel = new LoginModel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getMeal() {
        mealModel.getMeal(meal -> view.setMeals(meal));
    }

    @Override
    public void getUserProfile(String token) {
        userProfileModel.getUserProfile(token, new LoadUserInfoListener() {
            @Override
            public void loadUserProfile(UserProfile userProfile) {
                view.setUserProfile(userProfile);
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
                view.retryGetUserProfile(userToken);
                view.retryGetMeals(userToken);
            }

            @Override
            public void onFail(){
            }
        });
    }
}
