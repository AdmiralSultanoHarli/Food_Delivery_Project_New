package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTopSliderPager;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.BestCusineFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.CustomFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.TodaySpecialsFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.YourFavouritesFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    //private List<DataKhanaval> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private Context mContext;
    private TextView textViewMap;

    private static final String TAG = "Location Report";
    private String lastUpdateTime;
    private static final long WAIT_TIME = 10000;
    private static final int REQUEST_CHECK_SETTINGS = 100;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private SettingsClient settingsClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;
    private LocationCallback locationCallback;
    private Location location;

    // Boolean to toggle the ui
    private boolean requestingLocationUpdates;

    SearchView searchView;
    int[] img = {R.drawable.panang_curry, R.drawable.butter_chicken, R.drawable.maharashtra_thali, R.drawable.cashback, R.drawable.chow_mein};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView= inflater.inflate(R.layout.fragment_home_home, container, false);
        sliderPager = mView.findViewById(R.id.sliderPager);
        indicator = mView.findViewById(R.id.indicator);
        searchView = mView.findViewById(R.id.searchView);
        textViewMap = mView.findViewById(R.id.textViewMap);

       /* slideList = new ArrayList<>();
        slideList.add(new DataKhanaval(R.drawable.panang_curry, "PanangCurry", ));
        slideList.add(new DataFood(R.drawable.butter_chicken, "ButterChicken"));
        slideList.add(new DataFood(R.drawable.maharashtra_thali, "MaharashtraThali"));
        slideList.add(new DataFood(R.drawable.cashback, "Cashback"));
        slideList.add(new DataFood(R.drawable.chow_mein, "ChowMein"));*/

       searchView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               searchView.setIconified(false);

           }
       });

       textViewMap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                startLocationButtonClick();

           }
       });

       //startLocationButtonClick();

       init();
       restoreValuesFromBundle(savedInstanceState);

        ArrayList<DataKhanaval> dataKhanavals = getData();

        Log.d("context", String .valueOf(sliderPager));
        if(mContext!=null) {
            AdapterTopSliderPager adapter = new AdapterTopSliderPager(dataKhanavals, getActivity());
            sliderPager.setAdapter(adapter);
        }

        sliderPager.setPadding(50,0,50,0);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 0, 4000);

        indicator.setupWithViewPager(sliderPager, true);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BestCusineFragment bestCusineFragment = new BestCusineFragment();
        fragmentTransaction.add(R.id.bestCusineFragment, bestCusineFragment, bestCusineFragment.getTag());

        TodaySpecialsFragment todaySpecialsFragment = new TodaySpecialsFragment();
        fragmentTransaction.add(R.id.todaySpecialsFragment, todaySpecialsFragment, todaySpecialsFragment.getTag());

        YourFavouritesFragment yourFavouritesFragment = new YourFavouritesFragment();
        fragmentTransaction.add(R.id.yourFavouritesFragment, yourFavouritesFragment, yourFavouritesFragment.getTag());

        CustomFragment customFragment = new CustomFragment();
        fragmentTransaction.add(R.id.customFragment, customFragment, customFragment.getTag());

        fragmentTransaction.commit();

        return mView;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext =  context;
    }

    private void init(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        settingsClient = LocationServices.getSettingsClient(getContext());

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                //Location is received
                location = locationResult.getLastLocation();

                updateLocationUI();

            }
        };

        requestingLocationUpdates = false;

        locationRequest = new LocationRequest();
        locationRequest.setMaxWaitTime(WAIT_TIME);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();

    }

    /**
     * Restoring values from saved instance state
     */
    private void restoreValuesFromBundle(Bundle savedInstanceState){

        if (savedInstanceState != null){

            if (savedInstanceState.containsKey("is_requesting_updates")){

                requestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");

            }

            if (savedInstanceState.containsKey("last_known_location")){

                location = savedInstanceState.getParcelable("last_known_location");

            }

            if (savedInstanceState.containsKey("last_updated_on")){

                lastUpdateTime = savedInstanceState.getString("last_updated_on");

            }

        }

        updateLocationUI();

    }

    /**
     * Update the UI displaying the location data
     * and toggling the buttons
     */
    private void updateLocationUI(){

        if (location != null){

            getCompleteAddressString(location.getLatitude(), location.getLongitude());

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("is_requesting_updates", requestingLocationUpdates);
        outState.putParcelable("last_known_location", location);
        outState.putString("last_updated_on", lastUpdateTime);

    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */
    private void startLocationUpdates() {
        settingsClient
                .checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        Toast.makeText(getContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult((Activity) getContext(), REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    public void startLocationButtonClick() {
        // Requesting ACCESS_FINE_LOCATION using Dexter library
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        requestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        requestingLocationUpdates = false;
                        break;
                }
                break;
        }

    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (requestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();
    }



    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.e("My Current loction ", strReturnedAddress.toString());

                textViewMap.setText(strReturnedAddress.toString());

            } else {
                Log.e("My Current loction ", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("My Current loction ", "Cannot get Address!");
        }
        return strAdd;
    }


    private ArrayList<DataKhanaval> getData(){


        ArrayList<DataKhanaval> foodMenuArrayList = new ArrayList<>();
        for (int i = 0; i<img.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            foodMenuArrayList.add(dataKhanaval);

        }

        return foodMenuArrayList;

    }


    class SliderTimer extends TimerTask{

        @Override
        public void run() {

            if(getActivity() == null)
                return;


            getActivity().runOnUiThread(
                    new Runnable() {


                @Override
                public void run() {

                    if (sliderPager.getCurrentItem()<getData().size()-1){

                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);


                    }else {

                        sliderPager.setCurrentItem(0);

                    }
                }
            });



        }
    }

}
