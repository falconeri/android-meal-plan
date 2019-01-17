package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardTargetWeightActivity extends AppCompatActivity {
    Button btnStart;
    RadioGroup radioGrp;
    RadioButton radioBtn0, radioBtn1;
    Intent nextIntent;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_target_weight);

        radioGrp = findViewById(R.id.radioGrp);
        radioBtn0 = findViewById(R.id.radio0);
        radioBtn1 = findViewById(R.id.radio1);

        btnStart = findViewById(R.id.btnStart);
        nextIntent = new Intent(getApplicationContext(), OnBoardTargetGenderActivity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                savePreference(getApplicationContext(), Constants.JF_TARGET_WEIGHT, target);
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
