package simple.dms.myapplication.ui.badPoints;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import simple.dms.myapplication.R;
import simple.dms.myapplication.adapter.PointListAdapter;
import simple.dms.myapplication.model.data.PointList;
import simple.dms.myapplication.model.data.UserToken;

public class BadActivity extends AppCompatActivity implements BadContract.View{

    private final PointListAdapter pointListAdapter = new PointListAdapter();
    private BadContract.Presenter presenter;

    RecyclerView rv_point_history;
    ImageButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demerit_points);

        rv_point_history = findViewById(R.id.bad_list);
        rv_point_history.setAdapter(pointListAdapter);

        back = findViewById(R.id.back_btn_bad);
        back.setOnClickListener(v -> {
            finish();
        });

        presenter = new BadPresenter(this);
        presenter.getPoints(getToken());
    }

    @Override
    public void setBadPoints(PointList points) {
        pointListAdapter.setBadPoints(points);
    }

    @Override
    public void retrySetBadPoints(UserToken userToken) {
        setNewToken(userToken);
        presenter.refreshToken(getToken());
    }

    @Override
    public void tokenError() {
        presenter.refreshToken(getUserName());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public String getToken() {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        return "Bearer " + token.getString("AccessToken", "null");
    }

    public String getUserName() {
        SharedPreferences user = getSharedPreferences("UserToken", MODE_PRIVATE);
        return user.getString("Name", "null");
    }

    public void setNewToken(UserToken userToken) {
        SharedPreferences newToken = getSharedPreferences("UserToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = newToken.edit();
        editor.putString("AccessToken", userToken.getAccessToken());
        editor.putString("Name", userToken.getName());
        editor.apply();
    }
}
