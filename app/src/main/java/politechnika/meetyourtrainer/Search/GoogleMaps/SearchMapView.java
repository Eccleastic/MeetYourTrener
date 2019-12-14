package politechnika.meetyourtrainer.Search.GoogleMaps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import politechnika.meetyourtrainer.AdProvider;
import politechnika.meetyourtrainer.Profile.ProfileActivity;
import politechnika.meetyourtrainer.R;

public class SearchMapView extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    boolean locationPermissionGranted = false;
    double latitude, longitude;
    private Location lastLocation;
    private CameraPosition cameraPosition;
    private FusedLocationProviderClient fusedLocationProviderClient;
    boolean wasCentered = false;

    Thread threadLocalization;


    public static SearchMapView newInstance() {
        return new SearchMapView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map_view, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            lastLocation = savedInstanceState.getParcelable("location");
            cameraPosition = savedInstanceState.getParcelable("camera_position");
        }
        //https://medium.com/@droidbyme/get-current-location-using-fusedlocationproviderclient-in-android-cb7ebf5ab88e
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        super.onCreate(savedInstanceState);
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mGoogleMap != null) {
            outState.putParcelable("camera_position", mGoogleMap.getCameraPosition());
            outState.putParcelable("location", lastLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        getLocationPermission();
        updateLocation();
        getDeviceLocation();

        googleMap.setOnInfoWindowClickListener(this);

        //LOAD MARKERS FROM MARKERS PROVIDER CLASS
        MarkersProvider markersFromAPI = new MarkersProvider(0,0,0);
        markersFromAPI.createMarkers();
        int meetingId = 1;
        markersFromAPI.getMarkerById(meetingId, getActivity(), new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                LatLng coordinates = new LatLng(result.getDouble("latitude"), result.getDouble("longitude"));
                String title = result.getString("meeting_address").replace(" ", "");
                String description = result.getString("note").replace(" ", "") + " " + result.getInt("price") + "zl/h"
                        + result.getString("meeting_date").replace(" ", "");
                MarkerOptions marker = new MarkerOptions().position(coordinates).title(title).snippet(description);
                googleMap.addMarker(marker);
            }
        });
        List<MarkerOptions> markers = markersFromAPI.getMarkers();
        for (MarkerOptions markerOption : markers) {
            googleMap.addMarker(markerOption);
        }

        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(51.746956, 19.455958)).zoom(17).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

        /*threadLocalization = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(20000); //1000ms = 1 sec
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                getDeviceLocation();

                            }
                        });
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        threadLocalization.start();
        */

    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;

        } else {
            Toast.makeText(getActivity(), "Current location is null!", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "permission was granted  :)", Toast.LENGTH_LONG).show();
                }
                updateLocation();
            }
        }
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() == true) {
                            // Set the map's camera position to the current location of the device.
                            lastLocation = task.getResult();

                            latitude = lastLocation.getLatitude();
                            longitude = lastLocation.getLongitude();

                            if (!wasCentered) {
                                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17));
                                wasCentered = true;
                            }
                            Toast.makeText(getActivity(), "Current location: " + latitude + " , " + longitude, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Current location is null. Using defaults.", Toast.LENGTH_SHORT).show();
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.746956, 19.455958), 17));
                            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocation() {
        if (mGoogleMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mGoogleMap.setMyLocationEnabled(true);     //Twoja lokalizacja
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        AdProvider ad = new AdProvider("1");
        ad.setTitle(marker.getTitle());
        ad.setDescription(marker.getSnippet());
        Random r = new Random();
        double randomValue = 1.0 + (5.0 - 1.0) * r.nextDouble();
        ad.setRating(randomValue);
        intent.putExtra("description", ad.getDescription());
        intent.putExtra("title", ad.getTitle());
        intent.putExtra("rate", ad.getRating());
        startActivity(intent);
    }
}




