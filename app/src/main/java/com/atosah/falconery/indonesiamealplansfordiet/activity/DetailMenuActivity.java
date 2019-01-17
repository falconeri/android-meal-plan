package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.adapter.ImageAdapter;
import com.atosah.falconery.indonesiamealplansfordiet.adapter.MealAdapter;
import com.atosah.falconery.indonesiamealplansfordiet.adapter.RowListAdapter;
import com.atosah.falconery.indonesiamealplansfordiet.model.Menu;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class DetailMenuActivity extends AppCompatActivity {

    private static final String TAG = "DetailMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey("Menu")) {
                Menu menu = bundle.getParcelable("Menu");
                setDetailMenu(menu);
            }
        }
    }

    private void setDetailMenu(Menu menu) {

        ViewPager imageVP = findViewById(R.id.imageVP);
        CircleIndicator indicator = findViewById(R.id.indicator);
        TextView menuTV = findViewById(R.id.menuTV);
        TextView menuDescTV = findViewById(R.id.menuDescTV);

        ImageAdapter imageAdapter = new ImageAdapter(this, menu.getImages());
        imageVP.setAdapter(imageAdapter);
        indicator.setViewPager(imageVP);
        imageAdapter.registerDataSetObserver(indicator.getDataSetObserver());

        menuTV.setText(menu.getName());
        menuDescTV.setText(menu.getShortDesc());

        RecyclerView ingredientsRV = findViewById(R.id.ingredientsRV);
        RecyclerView stepsRV = findViewById(R.id.stepsRV);

        RecyclerView.LayoutManager ingredientLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager stepsLayoutManager = new LinearLayoutManager(this);

        ingredientsRV.setLayoutManager(ingredientLayoutManager);
        stepsRV.setLayoutManager(stepsLayoutManager);

        RowListAdapter ingredientAdapter = new RowListAdapter(this, menu.getIngredients(), false);
        RowListAdapter stepsAdapter = new RowListAdapter(this, menu.getSteps(), true);

        ingredientsRV.setAdapter(ingredientAdapter);
        stepsRV.setAdapter(stepsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
