package simple.dms.myapplication.model.callback;

import simple.dms.myapplication.model.data.UserProfile;

public interface LoadUserInfoListener {
    void loadUserProfile(UserProfile userProfile);
    void wrongToken();
}
