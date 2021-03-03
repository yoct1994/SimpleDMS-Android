package simple.dms.myapplication.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import simple.dms.myapplication.Methods.MaskToast;
import simple.dms.myapplication.R;
import simple.dms.myapplication.model.data.Login;
import simple.dms.myapplication.model.data.UserToken;
import simple.dms.myapplication.ui.main.MainActivity;
import simple.dms.myapplication.ui.mainpage.MainPageActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private SharedPreferences autoLogin;
    private LoginContract.Presenter presenter;

    private MaskToast maskToast;

    EditText login_Id;
    EditText login_Password;
    Button login_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        maskToast = new MaskToast();

        login_Id = (EditText) findViewById(R.id.login_edit);
        login_Password = (EditText) findViewById(R.id.login_edit2);
        login_btn = (Button) findViewById(R.id.login_btn);

        autoLogin = getSharedPreferences("AutoLogin", MODE_PRIVATE);

        login_btn.setOnClickListener(v -> {
            String id = login_Id.getText().toString();
            String password = login_Password.getText().toString();

            if(id.length() < 1 || password.length() < 1) {
                maskToast.setMaskToast("아이디와 비밀번호를 확인해주세요.", this);
                return;
            }

            presenter.login(new Login(id, password));
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail() {
        maskToast.setMaskToast("아이디와 비밀번호를 확인해주세요.", this);

        SharedPreferences.Editor autoLoginEditor = autoLogin.edit();
        autoLoginEditor.putBoolean("isAuto", false);
        autoLoginEditor.apply();
    }

    @Override
    public void setToken(UserToken token) {
        SharedPreferences sharedUserToken = getSharedPreferences("UserToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedUserToken.edit();
        editor.putString("Name", token.getName());
        editor.putString("AccessToken", token.getAccessToken());
        editor.apply();

        maskToast.setMaskToast("로그인 되었습니다.", this);

        editor = autoLogin.edit();
        editor.putBoolean("isAuto", true);
        editor.apply();

        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
        finish();
    }
}
