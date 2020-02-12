package com.example.fooddeliveryproject.Activities.Helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.security.PublicKey;
import java.sql.Array;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.SocketHandler;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.BUTTON_COLOR_PAYMENT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.COLOR_PAYMENT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.DATE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.EMAIL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_CATEGORY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DESCRIPTION;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DETAIL_IMG;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DETAIL_SCREEN_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DETAIL_SCREEN_TOTAL_DISCOUNT_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_DETAIL_SCREEN_TOTAL_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.GOPAY_BALANCE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IMAGE_PAYMENT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_ADDTOCART_VISIBLE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_COUPON_EXIST;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_FRAGMENT_ORDER_DETAILS_OPENED;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_FRAGMENT_ORDER_OPENED;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_THERE_IS_USER;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.LOCATION_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.LOCATION_OPENED;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MUAMALAT_BALANCE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.NO_ORDER_COMPLETE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.NUMBER;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.OVO_BALANCE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.PASSWORD;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.PAYMENT_METHOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.PAYMENT_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.SEARCH_OPENED;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.TOTAL_PAYMENT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.TOTAL_PAYMENT_SUCCESS;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.USERNAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.main_item;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.payment_method;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.sub_item;

public class SaveSharedPreference {

    static SharedPreferences getPreferences (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void setFoodCategory (Context context, String foodCategory){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_CATEGORY, foodCategory);
        editor.apply();

    }

    public static String getFoodCategory (Context context, String foodCategory){

        return getPreferences(context).getString(FOOD_CATEGORY, "");

    }


    public static void setFoodName(Context context, String foodName){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_NAME, foodName);
        editor.apply();

    }

    public static String getFoodName(Context context, String foodName){

        return getPreferences(context).getString(FOOD_NAME, "");

    }


    public static void setFoodDescription(Context context, String foodDescription){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_DESCRIPTION, foodDescription);
        editor.apply();

    }

    public static String getFoodDescription(Context context, String foodDescription){

        return getPreferences(context).getString(FOOD_DESCRIPTION, "");

    }


    public static void setQuantity(Context context, int quantity){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(QUANTITY, quantity);
        editor.apply();

    }

    public static int getQuantity(Context context, int quantity){

        return getPreferences(context).getInt(QUANTITY, 0);

    }


    public static void setAllQuantity(Context context, int allQuantity){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(ALL_QUANTITY, allQuantity);
        editor.apply();

    }

    public static int getAllQuantity(Context context, int allQuantity){

        return getPreferences(context).getInt(ALL_QUANTITY, 0);

    }


    public static void setFoodPrice(Context context, int foodPrice){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE, foodPrice);
        editor.apply();

    }

    public static int getFoodPrice(Context context, int foodPrice){

        return getPreferences(context).getInt(FOOD_PRICE, 0);

    }


    public static void setFoodPriceDiscount(Context context, int foodPriceDiscount){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_DISCOUNT, foodPriceDiscount);
        editor.apply();

    }

    public static int getFoodPriceDiscount(Context context, int foodPriceDiscount){

        return getPreferences(context).getInt(FOOD_PRICE_DISCOUNT, 0);

    }


    public static void setFoodPriceTotal(Context context, int foodPriceTotal){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
        editor.apply();

    }

    public static int getFoodPriceTotal(Context context, int foodPriceTotal){

        return getPreferences(context).getInt(FOOD_PRICE_TOTAL, 0);

    }


    public static void setFoodPriceDiscountTotal (Context context, int foodPriceDiscountTotal){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
        editor.apply();

    }

    public static int getFoodPriceDiscountTotal (Context context, int foodPriceDiscountTotal){

        return getPreferences(context).getInt(FOOD_PRICE_DISCOUNT_TOTAL, 0);

    }


    public static void setUsername(Context context, String username){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(USERNAME, username);
        editor.apply();

    }

    public static String getUsername(Context context, String username){

        return getPreferences(context).getString(USERNAME, "");

    }


    public static void setPassword(Context context, String password){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PASSWORD, password);
        editor.apply();

    }

    public static String getPassword(Context context, String password){

        return getPreferences(context).getString(PASSWORD, "");

    }


    public static void setEmail(Context context, String email){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();

    }

    public static String getEmail(Context context, String email){

        return getPreferences(context).getString(EMAIL, "");

    }


    public static void setNumber(Context context, String number){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NUMBER, number);
        editor.apply();

    }

    public static String getNumber(Context context, String number){

        return getPreferences(context).getString(NUMBER, "");

    }


    public static void setIsAddToCartVisible(Context context, boolean isAddToCartVisble){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_ADDTOCART_VISIBLE, isAddToCartVisble);
        editor.apply();

    }

    public static boolean getIsAddToCartVisible(Context context, boolean isAddToCart){
        return getPreferences(context).getBoolean(IS_ADDTOCART_VISIBLE, true);
    }


    public static void setFragmentOrderOpened(Context context, boolean isFragmentOrderOpened){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_FRAGMENT_ORDER_OPENED, isFragmentOrderOpened);
        editor.apply();

    }

    public static boolean getFragmentOrderOpened(Context context, boolean isFragmentOrderOpened){

        return getPreferences(context).getBoolean(IS_FRAGMENT_ORDER_OPENED, false);

    }


    public static void setFragmentOrderDetailsOpened(Context context, boolean isFragmentOrderDetailsOpened){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_FRAGMENT_ORDER_DETAILS_OPENED, isFragmentOrderDetailsOpened);
        editor.apply();

    }

    public static boolean getFragmentOrderDetailsOpened(Context context, boolean isFragmentOrderDetailsOpened){

        return getPreferences(context).getBoolean(IS_FRAGMENT_ORDER_DETAILS_OPENED, false);

    }


    public static void setFragmentOrderSubDetailsOpened(Context context, int sub_items){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(sub_item, sub_items);
        editor.apply();

    }

    public static int getFragmentOrderSubDetailsOpened(Context context, int sub_items){

        return getPreferences(context).getInt(sub_item, 0);

    }


    public static void setFragmentOrderMenuDetailsOpened(Context context, int items){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(main_item, items);
        editor.apply();

    }

    public static int getFragmentOrderMenuDetailsOpened(Context context, int items){

        return getPreferences(context).getInt(main_item, 0);

    }


    public static void setThereIsUser(Context context, boolean isThereIsUser){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_THERE_IS_USER, isThereIsUser);
        editor.apply();

    }

    public static boolean getThereIsUser(Context context, boolean isThereIsUser){

        return getPreferences(context).getBoolean(IS_THERE_IS_USER, false);

    }


    public static void setPayment(Context context, int payment){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(payment_method, payment);
        editor.apply();

    }

    public static int getPayment(Context context, int payment){

        return getPreferences(context).getInt(payment_method, 0);

    }


    public static void setSearchOpened(Context context, boolean isSearchOpened){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(SEARCH_OPENED, isSearchOpened);
        editor.apply();

    }

    public static boolean getSearchOpened(Context context, boolean isSearchOpened){

        return getPreferences(context).getBoolean(SEARCH_OPENED, false);

    }


    public static void setTotalPayment(Context context, int totalPayment){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(TOTAL_PAYMENT, totalPayment);
        editor.apply();

    }

    public static int getTotalPayment(Context context, int totalPayment){

        return getPreferences(context).getInt(TOTAL_PAYMENT, 0);

    }


    public static void setDate(Context context, String date){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DATE, date);
        editor.apply();

    }

    public static String getDate(Context context, String date){

        return getPreferences(context).getString(DATE, "");

    }


    public static void setImagePayment(Context context, int imagePayment){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(IMAGE_PAYMENT, imagePayment);
        editor.apply();

    }

    public static int getImagePayment(Context context, int imagePayment){

        return getPreferences(context).getInt(IMAGE_PAYMENT, 0);

    }


    public static void setColorPayment(Context context, int colorPayment){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(COLOR_PAYMENT, colorPayment);
        editor.apply();

    }

    public static int getColorPayment(Context context, int colorPayment){

        return getPreferences(context).getInt(COLOR_PAYMENT, 0);

    }


    public static void setButtonColor(Context context, int buttonColor){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(BUTTON_COLOR_PAYMENT, buttonColor);
        editor.apply();

    }

    public static int getButtonColor(Context context, int buttonColor){

        return getPreferences(context).getInt(BUTTON_COLOR_PAYMENT, 0);

    }


    public static void setPaymentName(Context context, String paymentName){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PAYMENT_NAME, paymentName);
        editor.apply();

    }

    public static String getPaymentName(Context context, String paymentName){

        return getPreferences(context).getString(PAYMENT_NAME, "");

    }


    public static void setOvoBalance(Context context, int ovoBalance){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(OVO_BALANCE, ovoBalance);
        editor.apply();

    }

    public static int getOvoBalance(Context context, int ovoBalance){

        return getPreferences(context).getInt(OVO_BALANCE, 200000);

    }


    public static void setGopayBalance(Context context, int gopayBalance){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(GOPAY_BALANCE, gopayBalance);
        editor.apply();

    }

    public static int getGopayBalance(Context context, int gopayBalance){

        return getPreferences(context).getInt(GOPAY_BALANCE, 200000);

    }


    public static void setPaymentMethodName(Context context, int paymentMethodNamae){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(PAYMENT_METHOD_NAME, paymentMethodNamae);
        editor.apply();

    }

    public static int getPaymentMethodName(Context context, int paymentMethodNamae){

        return getPreferences(context).getInt(PAYMENT_METHOD_NAME, 0);

    }


    public static void setLocationOpened(Context context, boolean locationOpened){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOCATION_OPENED, locationOpened);
        editor.apply();

    }

    public static boolean getLocationOpened(Context context, boolean locationOpened){

        return getPreferences(context).getBoolean(LOCATION_OPENED, false);

    }


    public static void setLocationName(Context context, String locationName){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOCATION_NAME, locationName);
        editor.apply();

    }

    public static String getLocationName(Context context, String locationName){

        return getPreferences(context).getString(LOCATION_NAME, "");

    }


    public static void setNoOrderComplete(Context context, int noOrderComplete){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(NO_ORDER_COMPLETE, noOrderComplete);
        editor.apply();

    }

    public static int getNoOrderComplete(Context context, int noOrderComplete){

        return getPreferences(context).getInt(NO_ORDER_COMPLETE, 0);

    }


    public static void setMuBalance(Context context, int muBalance){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(MUAMALAT_BALANCE, muBalance);
        editor.apply();

    }

    public static int getMubalance(Context context, int muBalance){

        return getPreferences(context).getInt(MUAMALAT_BALANCE, 200000);

    }

    public static void setIsCouponExist(Context context, boolean isCouponExist){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_COUPON_EXIST, isCouponExist);
        editor.apply();

    }

    public static boolean getIsCouponExist(Context context, boolean isCouponExist){

        return getPreferences(context).getBoolean(IS_COUPON_EXIST, false);

    }

    public static void setTotalPaymentSuccess(Context context, int totalPaymentSuccess){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(TOTAL_PAYMENT_SUCCESS, totalPaymentSuccess);
        editor.apply();

    }

    public static int getTotalPaymentSuccess(Context context, int totalPaymentSuccess){

        return getPreferences(context).getInt(TOTAL_PAYMENT_SUCCESS, 0);

    }

    public static void setFoodDetailName(Context context, String foodDetailName){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOOD_DETAIL_SCREEN_NAME, foodDetailName);
        editor.apply();

    }

    public static String getFoodDetailName(Context context, String foodDetailName){

        return getPreferences(context).getString(FOOD_DETAIL_SCREEN_NAME, "");

    }

    public static void setFoodDetailPrice(Context context, int foodDetailPrice){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_DETAIL_SCREEN_TOTAL_PRICE, foodDetailPrice);
        editor.apply();

    }

    public static int getFoodDetailPrice(Context context, int foodDetailPrice){

        return getPreferences(context).getInt(FOOD_DETAIL_SCREEN_TOTAL_PRICE, 0);

    }

    public static void setFoodDetailDiscountPrice(Context context, int foodDetailDiscountPrice){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_DETAIL_SCREEN_TOTAL_DISCOUNT_PRICE, foodDetailDiscountPrice);
        editor.apply();

    }

    public static int getFoodDetailDiscountPrice(Context context, int foodDetailDiscountPrice){

        return getPreferences(context).getInt(FOOD_DETAIL_SCREEN_TOTAL_DISCOUNT_PRICE, 0);

    }

    public static void setFoodDetailImg(Context context, int foodDetailImg){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(FOOD_DETAIL_IMG, foodDetailImg);
        editor.apply();

    }

    public static int getFoodDetailImg(Context context, int foodDetailImg){

        return getPreferences(context).getInt(FOOD_DETAIL_IMG, 0);

    }

}
