package simple.dms.myapplication.ui.late;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalTime;
import java.util.List;

import simple.dms.myapplication.Methods.MaskToast;
import simple.dms.myapplication.R;
import simple.dms.myapplication.model.data.LateSeat;
import simple.dms.myapplication.model.data.RequestLate;

public class LateActivity extends AppCompatActivity implements LateContract.View{

    private MaskToast maskToast;
    private LateContract.Presenter presenter;

    int clsNum;
    int stNum;

    CheckBox[] ga = new CheckBox[10];
    CheckBox[] na = new CheckBox[11];
    CheckBox[] da = new CheckBox[10];
    CheckBox[] la = new CheckBox[10];
    CheckBox[] f2 = new CheckBox[11];
    CheckBox[] f3 = new CheckBox[12];
    CheckBox[] f3_2 = new CheckBox[10];
    CheckBox[] f4 = new CheckBox[12];
    CheckBox[] f4_2 = new CheckBox[10];
    CheckBox[] f5 = new CheckBox[26];
    CheckBox[] sofa = new CheckBox[5];

    Button[] classes = new Button[11];

    ImageButton back;
    Button late;
    Button cancel;

    ConstraintLayout[] class_layout = new ConstraintLayout[11];

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late);

        maskToast = new MaskToast();
        presenter = new LatePresenter(this);

        clsNum = 1;

        ga[0] = (CheckBox)findViewById(R.id.ga1);
        ga[1] = (CheckBox)findViewById(R.id.ga2);
        ga[2] = (CheckBox)findViewById(R.id.ga3);
        ga[3] = (CheckBox)findViewById(R.id.ga4);
        ga[4] = (CheckBox)findViewById(R.id.ga5);
        ga[5] = (CheckBox)findViewById(R.id.ga6);
        ga[6] = (CheckBox)findViewById(R.id.ga7);
        ga[7] = (CheckBox)findViewById(R.id.ga8);
        ga[8] = (CheckBox)findViewById(R.id.ga9);
        ga[9] = (CheckBox)findViewById(R.id.ga10);

        na[0] = (CheckBox)findViewById(R.id.na1);
        na[1] = (CheckBox)findViewById(R.id.na2);
        na[2] = (CheckBox)findViewById(R.id.na3);
        na[3] = (CheckBox)findViewById(R.id.na4);
        na[4] = (CheckBox)findViewById(R.id.na5);
        na[5] = (CheckBox)findViewById(R.id.na6);
        na[6] = (CheckBox)findViewById(R.id.na7);
        na[7] = (CheckBox)findViewById(R.id.na8);
        na[8] = (CheckBox)findViewById(R.id.na9);
        na[9] = (CheckBox)findViewById(R.id.na10);
        na[10] = (CheckBox)findViewById(R.id.na11);

        da[0] = (CheckBox)findViewById(R.id.da1);
        da[1] = (CheckBox)findViewById(R.id.da2);
        da[2] = (CheckBox)findViewById(R.id.da3);
        da[3] = (CheckBox)findViewById(R.id.da4);
        da[4] = (CheckBox)findViewById(R.id.da5);
        da[5] = (CheckBox)findViewById(R.id.da6);
        da[6] = (CheckBox)findViewById(R.id.da7);
        da[7] = (CheckBox)findViewById(R.id.da8);
        da[8] = (CheckBox)findViewById(R.id.da9);
        da[9] = (CheckBox)findViewById(R.id.da10);

        la[0] = (CheckBox)findViewById(R.id.la1);
        la[1] = (CheckBox)findViewById(R.id.la2);
        la[2] = (CheckBox)findViewById(R.id.la3);
        la[3] = (CheckBox)findViewById(R.id.la4);
        la[4] = (CheckBox)findViewById(R.id.la5);
        la[5] = (CheckBox)findViewById(R.id.la6);
        la[6] = (CheckBox)findViewById(R.id.la7);
        la[7] = (CheckBox)findViewById(R.id.la8);
        la[8] = (CheckBox)findViewById(R.id.la9);
        la[9] = (CheckBox)findViewById(R.id.la10);

        f2[0] = (CheckBox)findViewById(R.id.f2_1);
        f2[1] = (CheckBox)findViewById(R.id.f2_2);
        f2[2] = (CheckBox)findViewById(R.id.f2_3);
        f2[3] = (CheckBox)findViewById(R.id.f2_4);
        f2[4] = (CheckBox)findViewById(R.id.f2_5);
        f2[5] = (CheckBox)findViewById(R.id.f2_6);
        f2[6] = (CheckBox)findViewById(R.id.f2_7);
        f2[7] = (CheckBox)findViewById(R.id.f2_8);
        f2[8] = (CheckBox)findViewById(R.id.f2_9);
        f2[9] = (CheckBox)findViewById(R.id.f2_10);
        f2[10] = (CheckBox)findViewById(R.id.f2_11);

        f3[0] = (CheckBox)findViewById(R.id.f3_1);
        f3[1] = (CheckBox)findViewById(R.id.f3_2);
        f3[2] = (CheckBox)findViewById(R.id.f3_3);
        f3[3] = (CheckBox)findViewById(R.id.f3_4);
        f3[4] = (CheckBox)findViewById(R.id.f3_5);
        f3[5] = (CheckBox)findViewById(R.id.f3_6);
        f3[6] = (CheckBox)findViewById(R.id.f3_7);
        f3[7] = (CheckBox)findViewById(R.id.f3_8);
        f3[8] = (CheckBox)findViewById(R.id.f3_9);
        f3[9] = (CheckBox)findViewById(R.id.f3_10);
        f3[10] = (CheckBox)findViewById(R.id.f3_11);
        f3[11] = (CheckBox)findViewById(R.id.f3_12);

        f3_2[0] = (CheckBox)findViewById(R.id.f3_2_1);
        f3_2[1] = (CheckBox)findViewById(R.id.f3_2_2);
        f3_2[2] = (CheckBox)findViewById(R.id.f3_2_3);
        f3_2[3] = (CheckBox)findViewById(R.id.f3_2_4);
        f3_2[4] = (CheckBox)findViewById(R.id.f3_2_5);
        f3_2[5] = (CheckBox)findViewById(R.id.f3_2_6);
        f3_2[6] = (CheckBox)findViewById(R.id.f3_2_7);
        f3_2[7] = (CheckBox)findViewById(R.id.f3_2_8);
        f3_2[8] = (CheckBox)findViewById(R.id.f3_2_9);
        f3_2[9] = (CheckBox)findViewById(R.id.f3_2_10);

        f4[0] = (CheckBox)findViewById(R.id.f4_1);
        f4[1] = (CheckBox)findViewById(R.id.f4_2);
        f4[2] = (CheckBox)findViewById(R.id.f4_3);
        f4[3] = (CheckBox)findViewById(R.id.f4_4);
        f4[4] = (CheckBox)findViewById(R.id.f4_5);
        f4[5] = (CheckBox)findViewById(R.id.f4_6);
        f4[6] = (CheckBox)findViewById(R.id.f4_7);
        f4[7] = (CheckBox)findViewById(R.id.f4_8);
        f4[8] = (CheckBox)findViewById(R.id.f4_9);
        f4[9] = (CheckBox)findViewById(R.id.f4_10);
        f4[10] = (CheckBox)findViewById(R.id.f4_11);
        f4[11] = (CheckBox)findViewById(R.id.f4_12);

        f4_2[0] = (CheckBox)findViewById(R.id.f4_2_1);
        f4_2[1] = (CheckBox)findViewById(R.id.f4_2_2);
        f4_2[2] = (CheckBox)findViewById(R.id.f4_2_3);
        f4_2[3] = (CheckBox)findViewById(R.id.f4_2_4);
        f4_2[4] = (CheckBox)findViewById(R.id.f4_2_5);
        f4_2[5] = (CheckBox)findViewById(R.id.f4_2_6);
        f4_2[6] = (CheckBox)findViewById(R.id.f4_2_7);
        f4_2[7] = (CheckBox)findViewById(R.id.f4_2_8);
        f4_2[8] = (CheckBox)findViewById(R.id.f4_2_9);
        f4_2[9] = (CheckBox)findViewById(R.id.f4_2_10);

        f5[0] = (CheckBox)findViewById(R.id.f5_1);
        f5[1] = (CheckBox)findViewById(R.id.f5_2);
        f5[2] = (CheckBox)findViewById(R.id.f5_3);
        f5[3] = (CheckBox)findViewById(R.id.f5_4);
        f5[4] = (CheckBox)findViewById(R.id.f5_5);
        f5[5] = (CheckBox)findViewById(R.id.f5_6);
        f5[6] = (CheckBox)findViewById(R.id.f5_7);
        f5[7] = (CheckBox)findViewById(R.id.f5_8);
        f5[8] = (CheckBox)findViewById(R.id.f5_9);
        f5[9] = (CheckBox)findViewById(R.id.f5_10);
        f5[10] = (CheckBox)findViewById(R.id.f5_11);
        f5[11] = (CheckBox)findViewById(R.id.f5_12);
        f5[12] = (CheckBox)findViewById(R.id.f5_13);
        f5[13] = (CheckBox)findViewById(R.id.f5_14);
        f5[14] = (CheckBox)findViewById(R.id.f5_15);
        f5[15] = (CheckBox)findViewById(R.id.f5_16);
        f5[16] = (CheckBox)findViewById(R.id.f5_17);
        f5[17] = (CheckBox)findViewById(R.id.f5_18);
        f5[18] = (CheckBox)findViewById(R.id.f5_19);
        f5[19] = (CheckBox)findViewById(R.id.f5_20);
        f5[20] = (CheckBox)findViewById(R.id.f5_21);
        f5[21] = (CheckBox)findViewById(R.id.f5_22);
        f5[22] = (CheckBox)findViewById(R.id.f5_23);
        f5[23] = (CheckBox)findViewById(R.id.f5_24);
        f5[24] = (CheckBox)findViewById(R.id.f5_25);
        f5[25] = (CheckBox)findViewById(R.id.f5_26);

        sofa[0] = (CheckBox)findViewById(R.id.so_1);
        sofa[1] = (CheckBox)findViewById(R.id.so_2);
        sofa[2] = (CheckBox)findViewById(R.id.so_3);
        sofa[3] = (CheckBox)findViewById(R.id.so_4);
        sofa[4] = (CheckBox)findViewById(R.id.so_5);

        class_layout[0] = (ConstraintLayout)findViewById(R.id.ga_layout);
        class_layout[1] = (ConstraintLayout)findViewById(R.id.na_layout);
        class_layout[2] = (ConstraintLayout)findViewById(R.id.da_layout);
        class_layout[3] = (ConstraintLayout)findViewById(R.id.la_layout);
        class_layout[4] = (ConstraintLayout)findViewById(R.id.f2_layout);
        class_layout[5] = (ConstraintLayout)findViewById(R.id.f3_layout);
        class_layout[6] = (ConstraintLayout)findViewById(R.id.f3_2_layout);
        class_layout[7] = (ConstraintLayout)findViewById(R.id.f4_layout);
        class_layout[8] = (ConstraintLayout)findViewById(R.id.f4_2_layout);
        class_layout[9] = (ConstraintLayout)findViewById(R.id.f5_layout);
        class_layout[10] = (ConstraintLayout)findViewById(R.id.so_layout);

        classes[0] = (Button)findViewById(R.id.ga_class);
        classes[1] = (Button)findViewById(R.id.na_class);
        classes[2] = (Button)findViewById(R.id.da_class);
        classes[3] = (Button)findViewById(R.id.la_class);
        classes[4] = (Button)findViewById(R.id.secF_class);
        classes[5] = (Button)findViewById(R.id.trdF_class);
        classes[6] = (Button)findViewById(R.id.trdF_class2);
        classes[7] = (Button)findViewById(R.id.forF_class);
        classes[8] = (Button)findViewById(R.id.forF_class2);
        classes[9] = (Button)findViewById(R.id.fiveF_class);
        classes[10] = (Button)findViewById(R.id.trdF_chair);

        back = (ImageButton)findViewById(R.id.back_btn_late);
        late = (Button)findViewById(R.id.late_btn);
        cancel = (Button)findViewById(R.id.late_cancel_btn);

        initCheckBox(ga);
        initCheckBox(na);
        initCheckBox(da);
        initCheckBox(la);
        initCheckBox(f2);
        initCheckBox(f3);
        initCheckBox(f3_2);
        initCheckBox(f4);
        initCheckBox(f4_2);
        initCheckBox(f5);
        initCheckBox(sofa);

        presenter.getLateInfo("1");
        presenter.getMyInfo(getUserName());

        for(int i = 0; i < classes.length; i++) {
            int I = i;
            classes[i].setOnClickListener(v -> {
                switch (clsNum) {
                    case 1:
                        setSeatCancel(ga);
                        break;
                    case 2:
                        setSeatCancel(na);
                        break;
                    case 3:
                        setSeatCancel(da);
                        break;
                    case 4:
                        setSeatCancel(la);
                        break;
                    case 5:
                        setSeatCancel(f2);
                        break;
                    case 6:
                        setSeatCancel(f3);
                        break;
                    case 7:
                        setSeatCancel(f3_2);
                        break;
                    case 8:
                        setSeatCancel(f4);
                        break;
                    case 9:
                        setSeatCancel(f4_2);
                        break;
                    case 10:
                        setSeatCancel(f5);
                        break;
                    case 11:
                        setSeatCancel(sofa);
                        break;
                }
                class_layout[I].setVisibility(View.VISIBLE);
                for(int j = 0; j < 11; j++) {
                    if(j != I) {
                        class_layout[j].setVisibility(View.INVISIBLE);
                    }
                }
                clsNum = I+1;
                presenter.getLateInfo(Integer.toString(clsNum+1));
                presenter.getMyInfo(getUserName());
            });
        }

        setCheckBoxesOption(ga);
        setCheckBoxesOption(na);
        setCheckBoxesOption(da);
        setCheckBoxesOption(la);
        setCheckBoxesOption(f2);
        setCheckBoxesOption(f3);
        setCheckBoxesOption(f3_2);
        setCheckBoxesOption(f4);
        setCheckBoxesOption(f4_2);
        setCheckBoxesOption(f5);
        setCheckBoxesOption(sofa);

        back.setOnClickListener(v -> {
            finish();
        });

        late.setOnClickListener(v -> {
            if(isDate()) {
                if (stNum != 0) {
                    presenter.postLate(new RequestLate(getUserName(), clsNum, stNum));

                    maskToast.setMaskToast("신청이 완료되었습니다.", this);
                } else {
                    maskToast.setMaskToast("자리를 선택해주세요.", this);
                }
            }else {
                maskToast.setMaskToast("신청할 시간이 아닙니다.", this);
            }
        });

        cancel.setOnClickListener(v -> {
            presenter.cancelLate(getUserName());
            presenter.getMyInfo(getUserName());
            presenter.getLateInfo(Integer.toString(clsNum));

            setSeatCancel(ga);
            setSeatCancel(na);
            setSeatCancel(da);
            setSeatCancel(la);
            setSeatCancel(f2);
            setSeatCancel(f3);
            setSeatCancel(f3_2);
            setSeatCancel(f4);
            setSeatCancel(f4_2);
            setSeatCancel(f5);
            setSeatCancel(sofa);
        });
    }

    @Override
    public void setMyLateInfo(boolean isMyInfo) {
        if(isMyInfo) {
            setSeatTrue(ga);
            setSeatTrue(na);
            setSeatTrue(da);
            setSeatTrue(la);
            setSeatTrue(f2);
            setSeatTrue(f3);
            setSeatTrue(f3_2);
            setSeatTrue(f4);
            setSeatTrue(f4_2);
            setSeatTrue(f5);
            setSeatTrue(sofa);
        }else {
            setSeatFalse(ga);
            setSeatFalse(na);
            setSeatFalse(da);
            setSeatFalse(la);
            setSeatFalse(f2);
            setSeatFalse(f3);
            setSeatFalse(f3_2);
            setSeatFalse(f4);
            setSeatFalse(f4_2);
            setSeatFalse(f5);
            setSeatFalse(sofa);
        }
    }

    public void setSeatTrue(CheckBox[] classes) {
        for(int i = 0; i < classes.length; i++) {
            classes[i].setEnabled(false);
        }
    }

    public void setSeatFalse(CheckBox[] classes) {
        for(int i = 0; i < classes.length; i++) {
            classes[i].setEnabled(true);
        }
    }

    public void setCheckBoxesOption(CheckBox[] classes) {
        for(int i = 0; i < classes.length; i++) {
            int I = i;
            classes[i].setOnCheckedChangeListener((buttonView, isChecked) -> {
                int J = I;
                if(isChecked) {
                    classes[I].setChecked(true);
                    classes[I].setText(getUserName().substring(4));
                    for(int j = 0; j < classes.length; j++) {
                        Log.d("txt1", classes[I].getText().toString());
                        Log.d("txt2", classes[j].getText().toString());
                        if(I != j) {
                            if(classes[I].getText().toString().equals(classes[j].getText().toString())) {
                                classes[j].setChecked(false);
                            }
                        }
                    }
                    stNum = J+1;
                }else {
                    classes[J].setText(Integer.toString(J+1));
                    stNum = 0;
                    Log.d("checked", Integer.toString(stNum));
                }
            });
        }
    }

    public void setNext(CheckBox[] cls1,
                        CheckBox[] cls2,
                        CheckBox[] cls3,
                        CheckBox[] cls4,
                        CheckBox[] cls5,
                        CheckBox[] cls6,
                        CheckBox[] cls7,
                        CheckBox[] cls8,
                        CheckBox[] cls9,
                        CheckBox[] cls10) {
        for(int i = 0; i < 26; i++) {
            if(i <= cls1.length) {
                if(cls1[i].getText().toString().equals(getUserName().substring(4))) {
                    cls1[i].setChecked(false);
                }
            }
            if(i <= cls2.length) {
                if(cls2[i].getText().toString().equals(getUserName().substring(4))) {
                    cls2[i].setChecked(false);
                }
            }
            if(i <= cls4.length) {
                if(cls3[i].getText().toString().equals(getUserName().substring(4))) {
                    cls4[i].setChecked(false);
                }
            }
            if(i <= cls5.length) {
                if(cls5[i].getText().toString().equals(getUserName().substring(4))) {
                    cls5[i].setChecked(false);
                }
            }
            if(i <= cls6.length) {
                if(cls6[i].getText().toString().equals(getUserName().substring(4))) {
                    cls6[i].setChecked(false);
                }
            }
            if(i <= cls7.length) {
                if(cls7[i].getText().toString().equals(getUserName().substring(4))) {
                    cls7[i].setChecked(false);
                }
            }
            if(i <= cls8.length) {
                if(cls8[i].getText().toString().equals(getUserName().substring(4))) {
                    cls8[i].setChecked(false);
                }
            }
            if(i <= cls9.length) {
                if(cls9[i].getText().toString().equals(getUserName().substring(4))) {
                    cls9[i].setChecked(false);
                }
            }
            if(i <= cls10.length){
                if(cls10[i].getText().toString().equals(getUserName().substring(4))) {
                    cls10[i].setChecked(false);
                }
            }
        }
    }

    public void initCheckBox(CheckBox[] checkBoxes) {
        for(int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setButtonDrawable(R.drawable.check_box_late);
        }
    }

    public void setSeat(CheckBox[] classes, List<LateSeat> lateSeats) {
        for(int i = 0; i < classes.length; i++) {
            for(LateSeat lateSeat : lateSeats) {
                if(i + 1 == lateSeat.getSeatNum()) {
                    classes[i].setChecked(true);
                    classes[i].setEnabled(false);
                    classes[i].setText(lateSeat.getName().substring(4));
                }
            }
        }
    }

    public void setSeatCancel(CheckBox[] classes) {
        for(int i = 0; i < classes.length; i++) {
            if(classes[i].getText().toString().equals(getUserName().substring(4))) {
                classes[i].setChecked(false);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isDate() {
        LocalTime now = LocalTime.now();
        LocalTime refTime = LocalTime.of(17, 30);

        return now.isAfter(refTime);
    }

    @Override
    public void setLateInfo(List<LateSeat> lateInfo) {
        if(!lateInfo.isEmpty()) {
            switch (lateInfo.get(0).getClassNum()) {
                case 1:
                    setSeat(ga, lateInfo);
                    break;
                case 2:
                    setSeat(na, lateInfo);
                    break;
                case 3:
                    setSeat(da, lateInfo);
                    break;
                case 4:
                    setSeat(la, lateInfo);
                    break;
                case 5:
                    setSeat(f2, lateInfo);
                    break;
                case 6:
                    setSeat(f3, lateInfo);
                    break;
                case 7:
                    setSeat(f3_2, lateInfo);
                    break;
                case 8:
                    setSeat(f4, lateInfo);
                    break;
                case 9:
                    setSeat(f4_2, lateInfo);
                    break;
                case 10:
                    setSeat(f5, lateInfo);
                    break;
                case 11:
                    setSeat(sofa, lateInfo);
                    break;
                default:
                    break;
            }
        }
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
