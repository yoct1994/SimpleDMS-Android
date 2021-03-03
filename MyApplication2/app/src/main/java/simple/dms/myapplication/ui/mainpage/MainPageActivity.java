package simple.dms.myapplication.ui.mainpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

import simple.dms.myapplication.R;
import simple.dms.myapplication.model.LoginModel;
import simple.dms.myapplication.model.data.Meal;
import simple.dms.myapplication.model.data.UserProfile;
import simple.dms.myapplication.model.data.UserToken;
import simple.dms.myapplication.ui.badPoints.BadActivity;
import simple.dms.myapplication.ui.goodPoints.GoodActivity;
import simple.dms.myapplication.ui.home.HomeActivity;
import simple.dms.myapplication.ui.late.LateActivity;
import simple.dms.myapplication.ui.login.LoginActivity;

public class MainPageActivity extends AppCompatActivity implements MainPageContract.View{

    private MainPagePresenter presenter;

    private LoginModel loginModel;

    Button bad_point;
    Button good_point;
    TextView good_points;
    TextView bad_points;
    TextView date;
    TextView user_name;
    TextView user;
    Button late;
    Button home;
    TextView logout;
    TextView breakfast;
    TextView lunch;
    TextView dinner;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        bad_point = (Button) findViewById(R.id.good);
        good_point = (Button) findViewById(R.id.bad);
        good_points = (TextView) findViewById(R.id.good_points);
        bad_points = (TextView) findViewById(R.id.bad_points);
        date = (TextView) findViewById(R.id.date);
        user_name = (TextView) findViewById(R.id.user_name);
        user = (TextView) findViewById(R.id.user);
        late = (Button) findViewById(R.id.late);
        home = (Button) findViewById(R.id.home);
        logout = (TextView) findViewById(R.id.logout);
        breakfast = (TextView) findViewById(R.id.breakfast_menu);
        lunch = (TextView) findViewById(R.id.lunch_menu);
        dinner = (TextView) findViewById(R.id.dinner_menu);

        logout.setOnClickListener(v -> {
            if(logout.isClickable()) {
                SharedPreferences autoLogin = getSharedPreferences("AutoLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = autoLogin.edit();
                editor.putBoolean("isAuto", false);
                editor.apply();

                gotoLogin();
            }
        });

        bad_point.setOnClickListener(v -> {
            Intent intent = new Intent(this, BadActivity.class);
            startActivity(intent);
        });

        good_point.setOnClickListener(v -> {
            Intent intent = new Intent(this, GoodActivity.class);
            startActivity(intent);
        });

        late.setOnClickListener(v -> {
            Intent intent = new Intent(this, LateActivity.class);
            startActivity(intent);
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        presenter = new MainPagePresenter(this);
        loginModel = new LoginModel();
        presenter.getUserProfile(getToken());
        presenter.getMeal();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setMeals(Meal meals) {
        StringBuilder breakfast_menu = new StringBuilder();
        StringBuilder lunch_menu = new StringBuilder();
        StringBuilder dinner_menu = new StringBuilder();
        StringBuilder to = new StringBuilder();
        if(meals.getBreakfast() != null) {
            for (String menu : meals.getBreakfast()) {
                breakfast_menu.append(menu).append(", ");
            }
        }else {
            breakfast_menu.append("급식이 없습니다.");
        }
        if(meals.getLunch() != null) {
            for (String menu : meals.getLunch()) {
                lunch_menu.append(menu).append(", ");
            }
        }else {
            lunch_menu.append("급식이 없습니다.");
        }

        if(meals.getDinner() != null) {
            for (String menu : meals.getDinner()) {
                dinner_menu.append(menu).append(", ");
            }
        }else {
            dinner_menu.append("급식이 없습니다.");
        }
        String today = LocalDate.now().toString();

        to.append(Integer.parseInt(today.substring(0,4)))
                .append("년")
                .append(today.substring(5,7))
                .append("월")
                .append(today.substring(8))
                .append("일");

        breakfast.setText(breakfast_menu);
        lunch.setText(lunch_menu);
        dinner.setText(dinner_menu);
        date.setText(to);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setUserProfile(UserProfile userProfile) {
        String name = userProfile.getName();
        int badPoints = userProfile.getBadPoint();
        int goodPoints = userProfile.getGoodPoint();

        int Number = userProfile.getNumber();
        StringBuilder user_num = new StringBuilder();
        String user = Integer.toString(Number);

        Integer grade = Integer.parseInt(user.substring(0,1));
        Integer cls = Integer.parseInt(user.substring(1,2));
        Integer num = Integer.parseInt(user.substring(2));

        user_num.append(grade).append("학년").append(cls).append("반").append(num).append("번");

        user_name.setText(name);
        this.user.setText(user_num);
        bad_points.setText(Integer.toString(badPoints));
        good_points.setText(Integer.toString(goodPoints));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void retryGetMeals(UserToken userToken) {
        setNewUserToken(userToken);
        presenter.getMeal();
    }

    @Override
    public void retryGetUserProfile(UserToken userToken) {
        setNewUserToken(userToken);
        presenter.getUserProfile(getToken());
    }

    @Override
    public void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void tokenError() {
        presenter.refreshToken(getUserName());
    }

    public String getToken() {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        return "Bearer " + token.getString("AccessToken", "null");
    }

    public String getUserName() {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        return token.getString("Name", "null");
    }

    public void setNewUserToken(UserToken newUserToken) {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = token.edit();
        editor.putString("AccessToken", newUserToken.getAccessToken());
        editor.putString("Name", newUserToken.getName());
        editor.apply();
    }
}

