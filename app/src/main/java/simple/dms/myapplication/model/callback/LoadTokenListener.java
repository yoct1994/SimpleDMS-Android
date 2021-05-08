package simple.dms.myapplication.model.callback;

import simple.dms.myapplication.model.data.UserToken;

public interface LoadTokenListener {
    void loadToken(UserToken userToken);
    void onFail();
}
