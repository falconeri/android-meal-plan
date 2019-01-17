package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.adapter.MealAdapter;
import com.atosah.falconery.indonesiamealplansfordiet.model.Menu;
import com.atosah.falconery.indonesiamealplansfordiet.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class MealListActivity extends AppCompatActivity {
    private RecyclerView breakfastRV;
    private RecyclerView lunchRV;
    private RecyclerView dinnerRV;

    private ArrayList<Menu> breakfastList;
    private ArrayList<Menu> lunchList;
    private ArrayList<Menu> dinnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        breakfastRV = findViewById(R.id.breakfastRV);
        lunchRV = findViewById(R.id.lunchRV);
        dinnerRV = findViewById(R.id.dinnerRV);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int pos = bundle.getInt("Position", 0);
        ArrayList<Plan> dayList = bundle.getParcelableArrayList("DayList");

        RecyclerView.LayoutManager breakfastLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager lunchLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager dinnerLayoutManager = new LinearLayoutManager(this);

        breakfastRV.setLayoutManager(breakfastLayoutManager);
        lunchRV.setLayoutManager(lunchLayoutManager);
        dinnerRV.setLayoutManager(dinnerLayoutManager);

        breakfastList = new ArrayList<>();
        lunchList = new ArrayList<>();
        dinnerList = new ArrayList<>();

        List<Menu> menus = dayList.get(pos).getMenus();
        for (int i = 0; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            if (menus.get(i).getType().equalsIgnoreCase("breakfast")) {
                breakfastList.add(menu);
            }

            if (menus.get(i).getType().equalsIgnoreCase("lunch")) {
                lunchList.add(menu);
            }

            if (menus.get(i).getType().equalsIgnoreCase("dinner")) {
                dinnerList.add(menu);
            }
        }

        MealAdapter breakfastAdapter = new MealAdapter(this, breakfastList);
        MealAdapter lunchAdapter = new MealAdapter(this, lunchList);
        MealAdapter dinnerAdapter = new MealAdapter(this, dinnerList);

        breakfastRV.setAdapter(breakfastAdapter);
        lunchRV.setAdapter(lunchAdapter);
        dinnerRV.setAdapter(dinnerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
