package com.example.fooddeliveryproject.Activities.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.security.PublicKey;
import java.util.logging.SocketHandler;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.EMAIL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_CATEGORY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DESCRIPTION;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.PASSWORD;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.USERNAME;

public class SaveSharedPreference {

    static SharedPreferences getPreferences (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    /**
     * setFoodCategory
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
     * getFoodCategory
     */
    public static String getFoodCategory (Context context, String foodCategory){

        return getPreferences(context).getString(FOOD_CATEGORY, "");

    }


    /**
     * setFoodName
     *
     * @param context
     * @param foodName
     */
    public static void setFoodName(Context context, String foodName){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_NAME, foodName);
        editor.apply();

    }

    /**
     * getFoodName
     */
    public static String getFoodName(Context context, String foodName){

        return getPreferences(context).getString(FOOD_NAME, "");

    }


    /**
     * setFoodDescription
     *
     * @param context
     * @param foodDescription
     */
    public static void setFoodDescription(Context context, String foodDescription){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_DESCRIPTION, foodDescription);
        editor.apply();

    }

    /**
     * getFoodDescription
     */
    public static String getFoodDescription(Context context, String foodDescription){

        return getPreferences(context).getString(FOOD_DESCRIPTION, "");

    }


    /**
     * setQuantity
     *
     * @param context
     * @param quantity
     */
    public static void setQuantity(Context context, int quantity){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(QUANTITY, quantity);
        editor.apply();

    }

    /**
     * getQuantity
     */
    public static int getQuantity(Context context, int quantity){

        return getPreferences(context).getInt(QUANTITY, 0);

    }


    /**
     * setAllQuantity
     *
     * @param context
     * @param allQuantity
     */
    public static void setAllQuantity(Context context, int allQuantity){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(ALL_QUANTITY, allQuantity);
        editor.apply();

    }

    /**
     * getAllQuantity
     */
    public static int getAllQuantity(Context context, int allQuantity){

        return getPreferences(context).getInt(ALL_QUANTITY, 0);

    }


    /**
     * setFoodPrice
     *
     * @param context
     * @param foodPrice
     */
    public static void setFoodPrice(Context context, int foodPrice){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE, foodPrice);
        editor.apply();

    }

    /**
     * getFoodPrice
     */
    public static int getFoodPrice(Context context, int foodPrice){

        return getPreferences(context).getInt(FOOD_PRICE, 0);

    }


    /**
     * setFoodPriceDiscount
     *
     * @param context
     * @param foodPriceDiscount
     */
    public static void setFoodPriceDiscount(Context context, int foodPriceDiscount){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_DISCOUNT, foodPriceDiscount);
        editor.apply();

    }

    /**
     * getFoodPriceDiscount
     */
    public static int getFoodPriceDiscount(Context context, int foodPriceDiscount){

        return getPreferences(context).getInt(FOOD_PRICE_DISCOUNT, 0);

    }


    /**
     * setFoodPriceTotal
     *
     * @param context
     * @param foodPriceTotal
     */
    public static void setFoodPriceTotal(Context context, int foodPriceTotal){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
        editor.apply();

    }

    /**
     * getFoodPriceTotal
     */
    public static int getFoodPriceTotal(Context context, int foodPriceTotal){

        return getPreferences(context).getInt(FOOD_PRICE_TOTAL, 0);

    }


    /**
     * setFoodPriceDiscountTotal
     *
     * @param context
     * @param foodPriceDiscountTotal
     */
    public static void setFoodPriceDiscountTotal (Context context, int foodPriceDiscountTotal){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
        editor.apply();

    }

    /**
     * getFoodPriceDiscountTotal
     */
    public static int getFoodPriceDiscountTotal (Context context, int foodPriceDiscountTotal){

        return getPreferences(context).getInt(FOOD_PRICE_DISCOUNT_TOTAL, 0);

    }


    /**
     * setUsername
     *
     * @param context
     * @param username
     */
    public static void setUsername(Context context, String username){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(USERNAME, username);
        editor.apply();

    }

    /**
     * getUsername
     */
    public static String getUsername(Context context, String username){

        return getPreferences(context).getString(USERNAME, "");

    }


    /**
     * setPassword
     *
     * @param context
     * @param password
     */
    public static void setPassword(Context context, String password){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PASSWORD, password);
        editor.apply();

    }

    /**
     * getPassword
     */
    public static String getPassword(Context context, String password){

        return getPreferences(context).getString(PASSWORD, "");

    }

    /**
     * setEmail
     *
     * @param context
     * @param email
     */
    public static void setEmail(Context context, String email){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();

    }

    /**
     * getEmail
     */
    public static String getEmail(Context context, String email){

        return getPreferences(context).getString(EMAIL, "");

    }

}
