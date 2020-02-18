package com.example.fooddeliveryproject.Activities.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

import java.util.List;
import java.util.Locale;

public class MapsActivityScreen extends BaseActivity {

    float latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_screen);

        latitude = SaveSharedPreference.getLatitude(this, 0);
        longitude = SaveSharedPreference.getLongitude(this, 0);

        Intent intent = new PlacePicker.IntentBuilder()
                .setLatLong(latitude, longitude)
                .showLatLong(true)
                .setMapRawResourceStyle(R.raw.map_style)
                .setFabColor(R.color.colorButton)
                .setAddressRequired(true)
                .setPrimaryTextColor(R.color.colorBlack)
                .setSecondaryTextColor(R.color.colorBlack)
                .setMapType(MapType.NORMAL)
                .setMarkerDrawable(R.drawable.ic_marker)
                .setMapZoom(16f)
                .build(MapsActivityScreen.this);

        startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                try {
                    AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);
                    //String placeName = String.valueOf(addressData.getAddressList());

                    double latitude = addressData.getLatitude();
                    double longitude = addressData.getLongitude();

                    SaveSharedPreference.setLatitude(this, (float) latitude);
                    SaveSharedPreference.setLongitude(this, (float) longitude);

                    getCompleteAddressString(latitude, longitude);
                    //((TextView) findViewById(R.id.address_data_text_view)).setText(addressData.toString());

                    Intent i = new Intent(MapsActivityScreen.this, OrderScreenActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 3);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");

                }
                strAdd = strReturnedAddress.toString();
                String[] locationParts = strAdd.split(", ");
                String locationName = "";

                if (locationParts.length == 8 || locationParts.length == 4 || locationParts.length == 6 ||
                locationParts.length == 7){

                    locationName = locationParts[0];

                }else if (locationParts.length == 9 || locationParts.length == 10 || locationParts.length == 12){

                    locationName = locationParts[1];

                }

                Log.e("Location", locationName);

                Log.e("location parts size", String.valueOf(locationParts.length));

                Log.e("My Current location ", strReturnedAddress.toString());

                SaveSharedPreference.setLocationName(this, strAdd);
                SaveSharedPreference.setLocationSimpleName(this, locationName);

            } else {
                Log.e("My Current location ", "No Address returned!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("My Current location ", "Cannot get Address!");
        }
        return strAdd;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MapsActivityScreen.this, OrderScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
