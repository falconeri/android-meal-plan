package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

import java.util.Calendar;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.getAge;
import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.savePreference;

public class OnBoardCurrentDateBirthActivity extends AppCompatActivity {
    Button btnStart;
    String birthDate, age;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_current_date_birth);

        final DatePicker datePicker = findViewById(R.id.datePicker);

        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year - 17);
        datePicker.setMaxDate(c.getTimeInMillis());
        c.set(Calendar.YEAR, year - 45);
        datePicker.setMinDate(c.getTimeInMillis());

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save preferences
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                birthDate = Integer.toString(year).concat("-").concat(Integer.toString(month)).concat("-").concat(Integer.toString(day));
                savePreference(getApplicationContext(), Constants.JF_CURRENT_BIRTH_DATE, birthDate);

                age = getAge(year, month, day);
                savePreference(getApplicationContext(), Constants.JF_CURRENT_AGE, age);
                Intent intent = new Intent(getApplicationContext(), OnBoardTargetMealPlanActivity.class);
                startActivity(intent);
            }
        });
    }
}
