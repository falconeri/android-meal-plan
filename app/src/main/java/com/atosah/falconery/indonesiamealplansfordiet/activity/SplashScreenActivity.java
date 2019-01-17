package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.model.User;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.isUserHasCalorie;

public class SplashScreenActivity extends AppCompatActivity {
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (isUserHasCalorie(this)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), OnBoardStartActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
