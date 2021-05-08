package simple.dms.myapplication.ui.home;

import android.util.Log;

import simple.dms.myapplication.model.HomeModel;
import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.callback.LoadHomeListener;
import simple.dms.myapplication.model.data.Home;
import simple.dms.myapplication.model.data.PostHomeComing;

public class HomePresenter implements HomeContract.Presenter{

    HomeContract.View view;
    HomeModel homeModel;
    LoginModel loginModel;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        homeModel = new HomeModel();
        loginModel = new LoginModel();
    }

    @Override
    public void postHome(PostHomeComing postHomeComing) {
        homeModel.postHome(postHomeComing, () -> {
            view.wrongName();
        });
    }

    @Override
    public void getHomeInfo(String name) {
        homeModel.getHomeInfo(name, new LoadHomeListener() {
            @Override
            public void loadHomeInfo(Home home) {
                Log.d("presenterVal", home.getHomecoming().toString());
                view.setHomeInfo(home);
            }
        });
    }
}
