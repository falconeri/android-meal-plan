package com.atosah.falconery.indonesiamealplansfordiet.activity;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardTargetActivity extends AppCompatActivity {
    Button btnStart;
    RadioGroup radioGrp;
    RadioButton radioBtn0, radioBtn1, radioBtn2;
    Intent nextIntent;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_target);

        radioGrp = findViewById(R.id.radioGrp);
        radioBtn0 = findViewById(R.id.radio0);
        radioBtn1 = findViewById(R.id.radio1);
        radioBtn2 = findViewById(R.id.radio2);

        btnStart = findViewById(R.id.btnStart);
        nextIntent = new Intent(getApplicationContext(), OnBoardTargetWeightActivity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                savePreference(getApplicationContext(), Constants.JF_TARGET_PLAN, target);
                startActivity(nextIntent);
            }
        });

        btnStart.setAlpha(.5f);
        btnStart.setClickable(false);

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        nextIntent = new Intent(getApplicationContext(), OnBoardTargetWeightActivity.class);
                        target = "1";
                        break;
                    case R.id.radio1:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        nextIntent = new Intent(getApplicationContext(), OnBoardTargetGenderActivity.class);
                        target = "2";
                        break;
                    case R.id.radio2:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        nextIntent = new Intent(getApplicationContext(), OnBoardTargetWeightActivity.class);
                        target = "3";
                        break;
                    default:
                        btnStart.setAlpha(.5f);
                        btnStart.setClickable(false);
                        nextIntent = new Intent(getApplicationContext(), OnBoardTargetWeightActivity.class);
                        target = "2";
                        break;
                }
            }
        });
    }
}
