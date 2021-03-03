package simple.dms.myapplication.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import simple.dms.myapplication.Methods.MaskToast;
import simple.dms.myapplication.R;
import simple.dms.myapplication.model.data.UserToken;
import simple.dms.myapplication.ui.login.LoginActivity;
import simple.dms.myapplication.ui.mainpage.MainPageActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private int pressedTime = 0;
    MainPresenter presenter;
    Button login_btn;
    MaskToast maskToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        maskToast = new MaskToast();

        if(isAuto()) {
            Intent goMainPage = new Intent(this, MainPageActivity.class);
            startActivity(goMainPage);
            finish();
        }

        login_btn = (Button) findViewById(R.id.main_login_btn);
        login_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        if(pressedTime == 0) {
            maskToast.setMaskToast("한번더 누르면 종료됩니다.", this);
            pressedTime = (int) System.currentTimeMillis();
        }else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if(seconds > 4000) {
                maskToast.setMaskToast("한번더 누르면 종료됩니다.", this);
                pressedTime = 0;
            }else {
                super.onBackPressed();
                finish();
            }
        }
    }

    @Override
    public void setToken(UserToken userToken) {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = token.edit();
        editor.putString("AccessToken", userToken.getAccessToken());
        editor.putString("Name", userToken.getName());
        editor.apply();
    }

    public boolean isAuto() {
        SharedPreferences autoLogin = getSharedPreferences("AutoLogin", MODE_PRIVATE);
        return autoLogin.getBoolean("isAuto", false);
    }

    public String getToken() {
        SharedPreferences UserToken = getSharedPreferences("UserToken", MODE_PRIVATE);
        return UserToken.getString("AccessToken", "null");
    }
}
