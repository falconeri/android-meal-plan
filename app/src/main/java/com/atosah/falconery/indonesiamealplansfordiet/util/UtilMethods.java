package com.atosah.falconery.indonesiamealplansfordiet.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.activity.SplashScreenActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * @class UtilMethods
 * @brief Methods used randomly through out the projects are described here
 */

public class UtilMethods {
    //! to activate internet checking set APP_TEST_MODE to false
    private static final boolean APP_TEST_MODE = false;
    private static AlertDialog dialog = null;

    /**
     * @param context
     * @return true or false mentioning the device is connected or not
     * @brief checking the internet connection on run time
     */
    public static boolean isConnectedToInternet(Context context) {
        if (APP_TEST_MODE) {
            return true;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
            }
        }
        return false;
    }

    /**
     * @param context the application context
     * @param key     variable in which the value will be stored to be retrieved later
     * @param value   the value to store
     * @brief save int value with shared preference
     */
    public static void savePreference(Context context, String key, int value) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @param context the application context
     * @param key     variable from which the value will be retrieved
     * @return int
     * @brief retrieve int value from specific key
     */
    public static int getPreferenceInt(Context context, String key) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * @param context the application context
     * @param key     variable in which the value will be stored to be retrieved later
     * @param value   the value to store
     * @brief save String value with shared preference
     */
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param context the application context
     * @param key     variable from which the value will be retrieved
     * @return Sting
     * @brief retrieve String value from specific key
     */
    public static String getPreferenceString(Context context, String key) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static void clearAllPreference(Context context) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * @param activity the context of the activity
     * @brief methods for showing the soft keyboard by forced
     */
    public static void showSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }

    /**
     * @param activity the context of the activity
     * @brief methods for hiding the soft keyboard by forced
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * @param context the application context
     * @return Point containing the width and height
     * @brief methods for getting device window height and width via Point object
     */
    public static Point getWindowSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * @param context the application context
     * @param dp      the value in density pixel to be converted into pixel
     * @return pixel in int
     * @brief convert density pixel to standard pixel
     */
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * @brief interface used by showNoInternetDialog() methods
     */
    public interface InternetConnectionListener {
        public void onConnectionEstablished(int code);

        public void onUserCanceled(int code);
    }

    /**
     * @param context                    the application context
     * @param internetConnectionListener listener from which the method is called
     * @param headline                   headline text in String
     * @param body                       body text in String
     * @param positiveString             positive text in String
     * @param negativeString             negative text in String
     * @param code                       check flag for detecting the case when the class has multiple internet checking task
     * @brief methods for showing a custom no internet dialog
     */
    public static void showNoInternetDialog(final Context context, final InternetConnectionListener internetConnectionListener, final String headline, final String body,
                                            final String positiveString, final String negativeString, final int code) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog, null);
        ((TextView) view.findViewById(R.id.headlineTV)).setText(headline);
        ((TextView) view.findViewById(R.id.bodyTV)).setText(body);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setPositiveButton(positiveString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (isConnectedToInternet(context)) {
                            internetConnectionListener.onConnectionEstablished(code);
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            showNoInternetDialog(context, internetConnectionListener, headline, body, positiveString, negativeString, code);
                        }
                    }
                })
                .setNegativeButton(negativeString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        internetConnectionListener.onUserCanceled(code);
                        dialog.dismiss();
                    }
                })
                .setView(view)
                .setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    /**
     * @param context        the application context
     * @param heading        the headline text in String
     * @param body           the body text in String
     * @param positiveString positive text in String
     * @param negativeString negative text in String
     * @brief methods for showing a custom exit dialog
     */
    public static void showExitDialog(final Context context, final String heading, final String body, final String positiveString, final String negativeString) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog, null);
        ((TextView) view.findViewById(R.id.headlineTV)).setText(heading);
        ((TextView) view.findViewById(R.id.bodyTV)).setText(body);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setPositiveButton(positiveString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, SplashScreenActivity.class));
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton(negativeString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setView(view)
                .setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    /**
     * @param context the application context
     * @return true or false
     * @brief methods for checking any user data complete or not
     */
    public static boolean isUserHasCalorie(Context context) {
        if (!TextUtils.isEmpty(getPreferenceString(context, Constants.JF_TARGET_CALORIE))) {
            return true;
        } else {
            return false;
        }
    }

    public static void calculateCalorie(Context context) {
        String targetPlan, targetWeight, currentGender, currentActivityLevel, currentWeight, currentHeight, currentAge;
        Double bmr = 0.0;
        Double activityFactor = 0.0;
        Double calorie;

        targetPlan = getPreferenceString(context, Constants.JF_TARGET_PLAN);
        targetWeight = getPreferenceString(context, Constants.JF_TARGET_WEIGHT);
        currentGender = getPreferenceString(context, Constants.JF_CURRENT_GENDER);
        currentActivityLevel = getPreferenceString(context, Constants.JF_CURRENT_ACTIVITY_LEVEL);
        currentHeight = getPreferenceString(context, Constants.JF_CURRENT_HEIGHT);
        currentWeight = getPreferenceString(context, Constants.JF_CURRENT_WEIGHT);
        currentAge = getPreferenceString(context, Constants.JF_CURRENT_AGE);

        // Mifflin-St Jeor Equation
        // calculate BMR
        switch (currentGender) {
            case "1":
                // Men
                bmr = (10 * Double.parseDouble(currentWeight)) + (6.25 * Double.parseDouble(currentHeight)) - (5 * Double.parseDouble(currentAge)) + 5;
                break;
            case "2":
                // Women
                bmr = (10 * Double.parseDouble(currentWeight)) + (6.25 * Double.parseDouble(currentHeight)) - (5 * Double.parseDouble(currentAge)) - 161;
                break;
        }

        // search current calorie needs per day
        switch (currentActivityLevel) {
            case "1":
                activityFactor = 1.2;
                break;
            case "2":
                activityFactor = 1.375;
                break;
            case "3":
                activityFactor = 1.55;
                break;
            case "4":
                activityFactor = 1.725;
                break;
        }

        // calorie to maintain weight
        calorie = bmr * activityFactor;

        if (targetPlan.equalsIgnoreCase("1")) {
            // lose weight
            if (targetWeight.equalsIgnoreCase("1")) {
                // 0.5 Kg per week
                calorie = calorie - 500;
            } else {
                // 1 Kg per week
                calorie = calorie - 1000;
            }
        } else if (targetPlan.equalsIgnoreCase("3")) {
            // gain weight
            if (targetWeight.equalsIgnoreCase("1")) {
                // 0.5 Kg per week
                calorie = calorie + 500;
            } else {
                // 1 Kg per week
                calorie = calorie + 1000;
            }
        }

        // save
        savePreference(context, Constants.JF_TARGET_CALORIE, Double.toString(calorie));
    }

    public static String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("json/" + fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
