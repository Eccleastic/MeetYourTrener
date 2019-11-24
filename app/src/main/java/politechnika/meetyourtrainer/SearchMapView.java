package politechnika.meetyourtrainer;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SearchMapView extends Fragment implements OnMapReadyCallback {

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

    private final LatLng budynek_CTI = new LatLng(51.746956, 19.455958);
    private final LatLng budynek_centrum_sportu = new LatLng(51.746256, 19.451444);
    private final LatLng budynek_sukcesja = new LatLng(51.749201, 19.448128);
    private final LatLng budynek_WEEIA = new LatLng(51.752612, 19.453118);

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

        googleMap.addMarker(new MarkerOptions().position(budynek_CTI).title("Centrum Technologii Informatycznych"));
        googleMap.addMarker(new MarkerOptions().position(budynek_centrum_sportu).title("Centrum Sportu"));
        googleMap.addMarker(new MarkerOptions().position(budynek_sukcesja).title("Galeria Sukcesja"));
        googleMap.addMarker(new MarkerOptions().position(budynek_WEEIA).title("Wydzial EEIA"));

        CameraPosition Liberty = CameraPosition.builder().target(budynek_CTI).zoom(17).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

        threadLocalization = new Thread() {
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
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(budynek_CTI, 17));
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
}




