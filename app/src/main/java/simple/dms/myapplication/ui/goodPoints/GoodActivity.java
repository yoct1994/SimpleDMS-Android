package simple.dms.myapplication.ui.goodPoints;

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

public class GoodActivity extends AppCompatActivity implements GoodContract.View{

    private final PointListAdapter pointListAdapter = new PointListAdapter();
    private GoodContract.Presenter presenter;

    RecyclerView rv_goodPoints;
    ImageButton back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_good_points);

        presenter = new GoodPresenter(this);

        rv_goodPoints = (RecyclerView) findViewById(R.id.list_good);
        rv_goodPoints.setAdapter(pointListAdapter);
        presenter.getPoints(getToken());

        back_btn = (ImageButton) findViewById(R.id.back_btn_good);
        back_btn.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void setPoints(PointList points) {
        pointListAdapter.setGoodPoints(points);
    }

    @Override
    public void retrySetPoints(UserToken userToken) {
        setNewToken(userToken);
        presenter.getPoints(getToken());
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
