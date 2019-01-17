package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardCurrentWeightActivity extends AppCompatActivity {
    Button btnStart;
    Intent nextIntent;

    EditText editTxtWeight;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_current_weight);

        btnStart = findViewById(R.id.btnStart);
        nextIntent = new Intent(getApplicationContext(), OnBoardCurrentHeightActivity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                target = editTxtWeight.getText().toString();
                savePreference(getApplicationContext(), Constants.JF_CURRENT_WEIGHT, target);
                startActivity(nextIntent);
            }
        });

        btnStart.setAlpha(.5f);
        btnStart.setClickable(false);

        editTxtWeight = findViewById(R.id.editTxtWeight);
        editTxtWeight.addTextChangedListener(watcher);
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editTxtWeight.getText().toString().length() == 0 || editTxtWeight.getText().toString().equalsIgnoreCase("0")) {
                btnStart.setAlpha(.5f);
                btnStart.setClickable(false);
            } else {
                btnStart.setAlpha(1f);
                btnStart.setClickable(true);
            }
        }
    };
}
