package com.atosah.falconery.indonesiamealplansfordiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.adapter.DayAdapter;
import com.atosah.falconery.indonesiamealplansfordiet.model.Macros;
import com.atosah.falconery.indonesiamealplansfordiet.model.Plan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.clearAllPreference;
import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.loadJSONFromAsset;
import static com.atosah.falconery.indonesiamealplansfordiet.util.UtilMethods.getPreferenceString;

import com.atosah.falconery.indonesiamealplansfordiet.util.Constants;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Plan> dayList;
    private ListView dayListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayListView = findViewById(R.id.dayListView);

        initPlanList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                // reset all preferences
                clearAllPreference(getApplicationContext());

                // go to splash screen intent
                Intent nextIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(nextIntent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initPlanList() {
        String targetMealPlan = getPreferenceString(this, Constants.JF_TARGET_MEAL_PLAN);
        String jsonList;

        switch (targetMealPlan) {
            case "1":
                jsonList = "3_days_plan";
                break;
            case "2":
                jsonList = "7_days_plan";
                break;
            case "3":
                jsonList = "14_days_plan";
                break;
            default:
                jsonList = "3_days_plan";
                break;
        }
        String jsonString = loadJSONFromAsset(this, jsonList);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            dayList = new ArrayList<Plan>();

            for (int i = 0; i < jsonArray.length(); i++) {
                Plan plan = new Plan();
                plan.setId(jsonArray.getJSONObject(i).getString(Constants.JF_ID));
                plan.setTitle(jsonArray.getJSONObject(i).getString(Constants.JF_TITLE));

                List<com.atosah.falconery.indonesiamealplansfordiet.model.Menu> menuList = new ArrayList<>();
                JSONArray menusArray = jsonArray.getJSONObject(i).getJSONArray(Constants.JF_MENUS);
                for (int j = 0; j < menusArray.length(); j++) {
                    com.atosah.falconery.indonesiamealplansfordiet.model.Menu menuDiet = new com.atosah.falconery.indonesiamealplansfordiet.model.Menu();

                    menuDiet.setName(menusArray.getJSONObject(j).getString(Constants.JF_MENUS_NAME));
                    menuDiet.setShortDesc(menusArray.getJSONObject(j).getString(Constants.JF_MENUS_SHORT_DESC));
                    menuDiet.setCalorie(menusArray.getJSONObject(j).getString(Constants.JF_MENUS_CALORIE));
                    menuDiet.setType(menusArray.getJSONObject(j).getString(Constants.JF_MENUS_TYPE));

                    // macros
                    Macros macros = new Macros();
                    JSONObject macroObj = menusArray.getJSONObject(j).optJSONObject(Constants.JF_MENUS_MACROS);
                    macros.setFat(Double.parseDouble(macroObj.get(Constants.JF_MENUS_MACROS_FAT).toString()));
                    macros.setProtein(Double.parseDouble(macroObj.get(Constants.JF_MENUS_MACROS_PROTEIN).toString()));
                    macros.setCarbs(Double.parseDouble(macroObj.get(Constants.JF_MENUS_MACROS_CARBS).toString()));
                    menuDiet.setMacros(macros);

                    // images
                    List<String> images = new ArrayList<>();
                    JSONArray imagesArray = menusArray.getJSONObject(j).optJSONArray(Constants.JF_MENUS_IMAGES);
                    for (int k = 0; k < imagesArray.length(); k++) {
                        images.add(imagesArray.getString(k));
                    }

                    menuDiet.setImages(images);

                    // ingredients
                    List<String> ingredients = new ArrayList<>();
                    JSONArray ingredientArray = menusArray.getJSONObject(j).optJSONArray(Constants.JF_MENUS_INGREDIENTS);
                    for (int l = 0; l < ingredientArray.length(); l++) {
                        ingredients.add(ingredientArray.getString(l));
                    }

                    menuDiet.setIngredients(ingredients);

                    // steps
                    List<String> steps = new ArrayList<>();
                    JSONArray stepsArray = menusArray.getJSONObject(j).optJSONArray(Constants.JF_MENUS_STEPS);
                    for (int k = 0; k < stepsArray.length(); k++) {
                        steps.add(stepsArray.getString(k));
                    }

                    menuDiet.setSteps(steps);

                    // add menu to list
                    menuList.add(menuDiet);
                }
                plan.setMenus(menuList);


                dayList.add(plan);
            }

            //Log.d("SUPERBOO", "" + dayList.toString());

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dayListView.setAdapter(new DayAdapter(getApplicationContext(), dayList));
                    dayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), MealListActivity.class);

                            Bundle bundle = new Bundle();
                            //intent.putExtra("Position", position);
                            //intent.putParcelableArrayListExtra("DayList", dayList);
                            bundle.putInt("Position", position);
                            bundle.putParcelableArrayList("DayList", dayList);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
