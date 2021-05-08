package simple.dms.myapplication.ui.late;

import simple.dms.myapplication.model.LateModel;
import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.callback.LoadMyLateInfoListener;
import simple.dms.myapplication.model.data.RequestLate;

public class LatePresenter implements LateContract.Presenter {

    LateContract.View view;
    LateModel lateModel;
    LoginModel loginModel;

    public LatePresenter(LateContract.View view) {
        this.view = view;
        lateModel = new LateModel();
        loginModel = new LoginModel();
    }

    @Override
    public void getLateInfo(String classNum) {
        lateModel.getLate(classNum, lateSeats -> view.setLateInfo(lateSeats));
    }

    @Override
    public void postLate(RequestLate requestLate) {
        lateModel.postLate(requestLate, () -> view.wrongName());
    }

    @Override
    public void cancelLate(String name) {
        lateModel.cancelLate(name, () -> {
            view.wrongName();
        });
    }

    @Override
    public void getMyInfo(String name) {
        lateModel.getMyInfo(name, new LoadMyLateInfoListener() {
            @Override
            public void wrongName() {
                view.wrongName();
            }

            @Override
            public void getMyInfo(boolean isMyInfo) {
                view.setMyLateInfo(isMyInfo);
            }
        });
    }
}
