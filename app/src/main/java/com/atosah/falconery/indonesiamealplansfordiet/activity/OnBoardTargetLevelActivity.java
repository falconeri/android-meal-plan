package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardTargetLevelActivity extends AppCompatActivity {
    Button btnStart;
    RadioGroup radioGrp;
    RadioButton radioBtn0, radioBtn1, radioBtn2, radioBtn3;
    Intent nextIntent;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_target_level);

        radioGrp = findViewById(R.id.radioGrp);
        radioBtn0 = findViewById(R.id.radio0);
        radioBtn1 = findViewById(R.id.radio1);
        radioBtn2 = findViewById(R.id.radio2);
        radioBtn3 = findViewById(R.id.radio3);

        btnStart = findViewById(R.id.btnStart);
        nextIntent = new Intent(getApplicationContext(), OnBoardCurrentWeightActivity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                savePreference(getApplicationContext(), Constants.JF_CURRENT_ACTIVITY_LEVEL, target);
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
                    case R.id.radio3:
                        btnStart.setAlpha(1f);
                        btnStart.setClickable(true);
                        target = "4";
                        break;
                    default:
                        btnStart.setAlpha(.5f);
                        btnStart.setClickable(false);
                        target = "1";
                        break;
                }
            }
        });

        // set title & subtitle
        radioBtn0.setText(Html.fromHtml("<b>" + getString(R.string.target_level_no_activity) + "</b>" + "<br />" +
                "<small> <font color='" + Color.GRAY + "'>" + getString(R.string.target_level_no_activity_subtitle) + "</font></small>"
        ));
        radioBtn1.setText(Html.fromHtml("<b>" + getString(R.string.target_level_low_activity) + "</b>" + "<br />" +
                "<small> <font color='" + Color.GRAY + "'>" + getString(R.string.target_level_low_activity_subtitle) + "</font></small>"
        ));
        radioBtn2.setText(Html.fromHtml("<b>" + getString(R.string.target_level_medium_activity) + "</b>" + "<br />" +
                "<small> <font color='" + Color.GRAY + "'>" + getString(R.string.target_level_medium_activity_subtitle) + "</font></small>"
        ));
        radioBtn3.setText(Html.fromHtml("<b>" + getString(R.string.target_level_high_activity) + "</b>" + "<br />" +
                "<small> <font color='" + Color.GRAY + "'>" + getString(R.string.target_level_high_activity_subtitle) + "</font></small>"
        ));
    }
}
