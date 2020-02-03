package com.example.fooddeliveryproject.Activities.Helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalHelper {

    Locale locale;
    DecimalFormatSymbols formatSymbols;
    DecimalFormat decimalFormat;

    public DecimalHelper() {

        locale = Locale.getDefault();
        formatSymbols = new DecimalFormatSymbols(locale);
        formatSymbols.setDecimalSeparator(',');
        formatSymbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("", formatSymbols);

    }

    public String formatter(int value ){

        return String.valueOf(decimalFormat.format(value));

    }

}
