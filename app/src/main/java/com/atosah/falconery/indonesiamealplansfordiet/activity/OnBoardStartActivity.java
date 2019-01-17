package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atosah.falconery.indonesiamealplansfordiet.R;

public class OnBoardStartActivity extends AppCompatActivity {

    Animation slide_center_to_top, fade_in;
    ImageView imgViewLogo;
    Button btnStart;
    RelativeLayout relRel1;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relRel1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_start);

        slide_center_to_top = AnimationUtils.loadAnimation(this, R.anim.slide_to_top);
        slide_center_to_top.setFillAfter(true);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade_in.setFillAfter(true);

        imgViewLogo = findViewById(R.id.imgViewLogo);
        imgViewLogo.setAnimation(slide_center_to_top);
        slide_center_to_top.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                relRel1 = findViewById(R.id.relLay1);
                relRel1.animate().alpha(1.0f);
                relRel1.setAnimation(fade_in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OnBoardTargetActivity.class);
                startActivity(intent);
            }
        });
    }
}
