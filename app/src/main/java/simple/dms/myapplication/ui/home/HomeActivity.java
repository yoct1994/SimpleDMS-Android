package simple.dms.myapplication.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import simple.dms.myapplication.Methods.MaskToast;
import simple.dms.myapplication.R;
import simple.dms.myapplication.model.data.Home;
import simple.dms.myapplication.model.data.PostHomeComing;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    private HomeContract.Presenter presenter;

    String homeName;
    int value;

    private MaskToast maskToast;

    ConstraintLayout friday;
    ConstraintLayout saturday;
    ConstraintLayout saturday2;
    ConstraintLayout stay;
    CheckBox friday_ck;
    CheckBox saturday_ck;
    CheckBox saturday2_ck;
    CheckBox stay_ck;
    Button friday_btn;
    Button saturday_btn;
    Button saturday2_btn;
    Button stay_btn;
    Button homecoming;
    ImageButton back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        maskToast = new MaskToast();
        presenter = new HomePresenter(this);

        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        saturday2 = findViewById(R.id.saturday2);
        stay = findViewById(R.id.stay);
        friday_btn = findViewById(R.id.friday_btn);
        saturday_btn = findViewById(R.id.saturday_btn);
        saturday2_btn = findViewById(R.id.saturday2_btn);
        stay_btn = findViewById(R.id.stay_btn);
        friday_ck = findViewById(R.id.friday_ck);
        saturday_ck = findViewById(R.id.saturday_ck);
        saturday2_ck = findViewById(R.id.saturday2_ck);
        stay_ck = findViewById(R.id.stay_ck);
        back = findViewById(R.id.back_btn_home);
        homecoming = findViewById(R.id.home_btn);

        friday_ck.setButtonDrawable(R.drawable.large_checkbox);
        saturday_ck.setButtonDrawable(R.drawable.large_checkbox);
        saturday2_ck.setButtonDrawable(R.drawable.large_checkbox);
        stay_ck.setButtonDrawable(R.drawable.large_checkbox);

        friday_btn.setOnClickListener(v -> {
            friday.setVisibility(View.VISIBLE);
            saturday.setVisibility(View.INVISIBLE);
            saturday2.setVisibility(View.INVISIBLE);
            stay.setVisibility(View.INVISIBLE);
        });
        saturday_btn.setOnClickListener(v -> {
            friday.setVisibility(View.INVISIBLE);
            saturday.setVisibility(View.VISIBLE);
            saturday2.setVisibility(View.INVISIBLE);
            stay.setVisibility(View.INVISIBLE);
        });
        saturday2_btn.setOnClickListener(v -> {
            friday.setVisibility(View.INVISIBLE);
            saturday.setVisibility(View.INVISIBLE);
            saturday2.setVisibility(View.VISIBLE);
            stay.setVisibility(View.INVISIBLE);
        });
        stay_btn.setOnClickListener(v -> {
            friday.setVisibility(View.INVISIBLE);
            saturday.setVisibility(View.INVISIBLE);
            saturday2.setVisibility(View.INVISIBLE);
            stay.setVisibility(View.VISIBLE);
        });

        friday_ck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                saturday_ck.setChecked(false);
                saturday2_ck.setChecked(false);
                stay_ck.setChecked(false);

                homeName = "금요귀가";
                value = 1;
            }else {

                value = 0;
            }
        });

        saturday_ck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                friday_ck.setChecked(false);
                saturday2_ck.setChecked(false);
                stay_ck.setChecked(false);

                homeName = "토요귀가";
                value = 2;
            }else {

                value = 0;
            }
        });

        saturday2_ck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                friday_ck.setChecked(false);
                saturday_ck.setChecked(false);
                stay_ck.setChecked(false);

                homeName = "토요귀사";
                value = 3;
            }else {

                value = 0;
            }
        });

        stay_ck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                friday_ck.setChecked(false);
                saturday_ck.setChecked(false);
                saturday2_ck.setChecked(false);

                homeName = "잔류";
                value = 4;
            }else {

                value = 0;
            }
        });

        presenter.getHomeInfo(getUserName());

        homecoming.setOnClickListener(v -> {
            SharedPreferences homeDB = getSharedPreferences("homeValue", MODE_PRIVATE);
            SharedPreferences.Editor homeEditor = homeDB.edit();

            homeEditor.putString("home", homeName);
            homeEditor.putInt("value", value);
            homeEditor.apply();

            presenter.postHome(new PostHomeComing(getUserName(), value));

            maskToast.setMaskToast(homeName + "가 신청되었습니다", this);
        });

        Log.d("LastValue", Integer.toString(value));

        back.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void setHomeInfo(Home homeInfo) {
        SharedPreferences homeComing = getSharedPreferences("homeValue", MODE_PRIVATE);
        SharedPreferences.Editor HomeEditor = homeComing.edit();

        Log.d("value", homeInfo.getHomecoming().toString());

        switch(homeInfo.getHomecoming()) {
            case 1:
                homeName = "금요귀가";
                friday_ck.setChecked(true);
                break;
            case 2:
                homeName = "토요귀가";
                saturday_ck.setChecked(true);
                break;
            case 3:
                homeName = "토요귀사";
                saturday2_ck.setChecked(true);
                break;
            case 4:
                homeName = "잔류";
                stay_ck.setChecked(true);
                break;
            default:
                break;
        }

        value = homeInfo.getHomecoming();

        Log.d("methodVal", Integer.toString(value));

        HomeEditor.putString("home", homeName);
        HomeEditor.putInt("value", value);
        HomeEditor.apply();
    }

    @Override
    public void wrongName() {
        maskToast.setMaskToast("오류가 발생했습니다.", this);
    }

    public String getUserName() {
        SharedPreferences token = getSharedPreferences("UserToken", MODE_PRIVATE);
        return token.getString("Name", "null");
    }
}
