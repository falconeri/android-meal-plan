package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.calculateCalorie;
import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardTargetMealPlanActivity extends AppCompatActivity {
    Button btnStart;
    RadioGroup radioGrp;
    RadioButton radioBtn0, radioBtn1, radioBtn2;
    Intent nextIntent;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_target_meal_plan);

        radioGrp = findViewById(R.id.radioGrp);
        radioBtn0 = findViewById(R.id.radio0);
        radioBtn1 = findViewById(R.id.radio1);
        radioBtn2 = findViewById(R.id.radio2);

        btnStart = findViewById(R.id.btnStart);
        nextIntent = new Intent(getApplicationContext(), MainActivity.class);
        nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                savePreference(getApplicationContext(), Constants.JF_TARGET_MEAL_PLAN, target);
                calculateCalorie(getApplicationContext());
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
                        target = "1";
                        break;
                    case R.id.radio1:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        target = "2";
                        break;
                    case R.id.radio2:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        target = "3";
                        break;
                    default:
                        btnStart.setAlpha(.5f);
                        btnStart.setClickable(false);
                        target = "1";
                        break;
                }
            }
        });
    }
}
