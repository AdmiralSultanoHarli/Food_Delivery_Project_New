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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import com.example.fooddeliveryproject.Activities.Activity.HomeScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterSearchView;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.RestaurantsFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.SearchViewFragment;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTopSliderPager;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.CustomFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.BestCusineFragment;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    private ViewPager sliderPager;
    private TabLayout indicator;
    private Context mContext;
    private TextView textViewMap;
    public static LinearLayout searchFragment;
    public static ScrollView scrollView;
    public ImageView pinPoint;
    public RelativeLayout searchContainer;
    public TextView textFindLocation;
    TextView numberCountText;
    ImageView shoppingCartButton;
    Timer timer;

    private static final String TAG = "Location Report";
    private String lastUpdateTime;
    private static final long WAIT_TIME = 200;
    private static final int PRIORITY = 2000;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 100000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 50000;
    private static final int REQUEST_CHECK_SETTINGS = 100;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private SettingsClient settingsClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;
    private LocationCallback locationCallback;
    private Location location;

    boolean locationExist;

    // Boolean to toggle the ui
    private boolean requestingLocationUpdates;

    public static SearchView searchView;
    int[] img = {R.drawable.muamalat_cashback, R.drawable.domino_cashback, R.drawable.pizza_cashback, R.drawable.cashback, R.drawable.flipburger_cashback, R.drawable.cashback_s};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView= inflater.inflate(R.layout.fragment_home_home, container, false);
        sliderPager = mView.findViewById(R.id.sliderPager);
        indicator = mView.findViewById(R.id.indicator);
        searchView = mView.findViewById(R.id.searchView);
        textViewMap = mView.findViewById(R.id.textViewMap);
        searchFragment = mView.findViewById(R.id.searchFraegment);
        scrollView = mView.findViewById(R.id.scrollView);
        pinPoint = mView.findViewById(R.id.pinPoint);
        searchContainer = mView.findViewById(R.id.searchContainer);
        textFindLocation = mView.findViewById(R.id.textFindLocation);
        numberCountText = mView.findViewById(R.id.numberCountText);
        shoppingCartButton = mView.findViewById(R.id.shoppingCartButton);

        locationExist = false;
        init();

        timer = new Timer();

        if (SaveSharedPreference.getAllQuantity(mContext, 0) >= 1 ) {

            numberCountText.setVisibility(View.VISIBLE);
            numberCountText.setText(String.valueOf(SaveSharedPreference.getAllQuantity(getContext(), 0)));

            shoppingCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SaveSharedPreference.getAllQuantity(mContext, 0) >= 1 ) {

                        Intent i = new Intent(mContext, OrderScreenActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }
                }
            });

        }else {

            shoppingCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext, "There'is no item in the cart", Toast.LENGTH_SHORT).show();

                }
            });

        }

        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        textViewMap.setVisibility(View.VISIBLE);
        pinPoint.setVisibility(View.VISIBLE);
        textFindLocation.setVisibility(View.GONE);
        textViewMap.setText(SaveSharedPreference.getLocationSimpleName(getContext(), ""));


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeScreenActivity homeScreenActivity = (HomeScreenActivity) getActivity();
                homeScreenActivity.isSearchFragmentOpened = true;
                homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new SearchViewFragment()).addToBackStack(null).commit();

            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeScreenActivity homeScreenActivity = (HomeScreenActivity) getActivity();
                homeScreenActivity.isSearchFragmentOpened = true;
                homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new SearchViewFragment()).addToBackStack(null).commit();

            }
        });

        SaveSharedPreference.getLocationOpened(getContext(), false);
        Log.e("Location First", String.valueOf(SaveSharedPreference.getLocationOpened(mContext, false)));

        if (SaveSharedPreference.getLocationOpened(getContext(), false) == false){

            startLocationButtonClick();
            //startLocationUpdates();
            //locationOpened = SaveSharedPreference.getLocationOpened(mContext, false);
            Log.e("Location Opened updated", String.valueOf(SaveSharedPreference.getLocationOpened(mContext, false)));

        }

        Log.e("Location Opened", String.valueOf(SaveSharedPreference.getLocationOpened(mContext, false)));

        searchContainer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startLocationButtonClick();
               //startLocationUpdates();

           }
        });


        ArrayList<DataKhanaval> dataKhanavals = getData();

        Log.d("context", String .valueOf(sliderPager));
        if(mContext!=null) {
            AdapterTopSliderPager adapter = new AdapterTopSliderPager(dataKhanavals, getActivity());
            sliderPager.setAdapter(adapter);
        }

        sliderPager.setPadding(50,0,50,0);

        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 0, 4000);

        indicator.setupWithViewPager(sliderPager, true);

        RestaurantsFragment restaurantsFragment = new RestaurantsFragment();
        fragmentTransaction.replace(R.id.restaurantsFragment, restaurantsFragment, restaurantsFragment.getTag());

        BestCusineFragment bestCusineFragment = new BestCusineFragment();
        fragmentTransaction.replace(R.id.bestCusineFragment, bestCusineFragment, bestCusineFragment.getTag());

        YourFavouritesFragment yourFavouritesFragment = new YourFavouritesFragment();
        fragmentTransaction.replace(R.id.yourFavouritesFragment, yourFavouritesFragment, yourFavouritesFragment.getTag());

        CustomFragment customFragment = new CustomFragment();
        fragmentTransaction.replace(R.id.customFragment, customFragment, customFragment.getTag());

        fragmentTransaction.commit();

        init();

        return mView;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext =  context;
    }

    public void init(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
        settingsClient = LocationServices.getSettingsClient(mContext);

        locationCallback = new LocationCallback() {
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

        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        //locationRequest.setMaxWaitTime(WAIT_TIME);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();

    }


    /**
     * Update the UI displaying the location data
     * and toggling the buttons
     */
    public void updateLocationUI(){

        if (location != null) {

            getCompleteAddressString(location.getLatitude(), location.getLongitude());
            Log.e("Latitude", String.valueOf(location.getLatitude()));
            Log.e("Longitude", String.valueOf(location.getLongitude()));

            SaveSharedPreference.setLatitude(mContext, (float) location.getLatitude());
            SaveSharedPreference.setLongitude(mContext, (float) location.getLongitude());

        }

    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */
    public void startLocationUpdates() {
        settingsClient
                .checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                            //Toast.makeText(getContext(), "Started location updates!", Toast.LENGTH_SHORT).show();
                            SaveSharedPreference.setLocationOpened(getActivity(), true);

                            //noinspection MissingPermission
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                    locationCallback, Looper.myLooper());

                            updateLocationUI();
                        }
                    })
                    .addOnFailureListener((Activity) mContext, new OnFailureListener() {
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
                                        rae.startResolutionForResult((Activity) mContext, REQUEST_CHECK_SETTINGS);
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
        Dexter.withActivity((Activity) mContext)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        requestingLocationUpdates = true;
                        //SaveSharedPreference.setLocationOpened(mContext, true);
                        SaveSharedPreference.setLocationOpened(getActivity(), true);
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            Log.e("this button", "pressed");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User choose not to make required location settings changes.");
                        requestingLocationUpdates = false;
                        break;
                }
                break;
        }

    }

    public void stopLocationUpdates() {
        // Removing location updates
        fusedLocationProviderClient
                .removeLocationUpdates(locationCallback)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                          //Toast.makeText(mContext, "Location updates stopped!", Toast.LENGTH_SHORT).show();
                        Log.e("Location Stopped", " yes");
                    }
                });
    }

    public void openSettings() {
        /*Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        mContext.startActivity(intent);
    }

    /*@Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (requestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();

    }*/

    public boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;

    }

    /*@Override
    public void onPause() {
        super.onPause();

        if (requestingLocationUpdates) {
            // pausing location updates
            stopLocationUpdates();
        }
    }*/

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
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

                if (/*locationParts.length == 8 || locationParts.length == 4 || locationParts.length == 5
                        || locationParts.length == 6 || locationParts.length == 7*/locationParts.length < 9){

                    locationName = locationParts[0];

                }else if (/*locationParts.length == 9 || locationParts.length == 10 || locationParts.length == 12*/
                        locationParts.length >= 9){

                    locationName = locationParts[1];

                }

                Log.e("Location", locationName);

                Log.e("location parts size", String.valueOf(locationParts.length));

                Log.e("My Current location ", strReturnedAddress.toString());

                textViewMap.setVisibility(View.VISIBLE);
                pinPoint.setVisibility(View.VISIBLE);
                textFindLocation.setVisibility(View.GONE);
                textViewMap.setText(locationName);
                SaveSharedPreference.setLocationName(mContext, strAdd);
                SaveSharedPreference.setLocationSimpleName(mContext, locationName);

            } else {
                Log.e("My Current location ", "No Address returned!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("My Current location ", "Cannot get Address!");
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
