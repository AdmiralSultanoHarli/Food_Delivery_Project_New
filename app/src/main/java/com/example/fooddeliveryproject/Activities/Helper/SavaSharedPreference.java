package com.example.fooddeliveryproject.Activities.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_CATEGORY;

public class SavaSharedPreference {

    static SharedPreferences getPreferences (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set Food Category
     *
     * @param context
     * @param foodCategory
     */

    public static void setFoodCategory (Context context, String foodCategory){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_CATEGORY, foodCategory);
        editor.apply();

    }

    /**
     * get Food Category
     */
    public static String getFoodCategory (Context context){

        return getPreferences(context).getString(FOOD_CATEGORY, "");

    }

}
