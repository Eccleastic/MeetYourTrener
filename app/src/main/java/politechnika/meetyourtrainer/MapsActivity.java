package politechnika.meetyourtrainer;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private final LatLng budynek_CTI = new LatLng(51.746956, 19.455958);
    private final LatLng budynek_centrum_sportu = new LatLng(51.746256, 19.451444);
    private final LatLng budynek_sukcesja = new LatLng(51.749201, 19.448128);
    private final LatLng budynek_WEEIA = new LatLng(51.752612, 19.453118);
    TextView textView;
    double latitude, longitude;
    boolean locationPermissionGranted;
    boolean wasCentered = false;
    Marker marker;
    Thread threadLocalization;
    private GoogleMap mMap;
    private Location lastLocation;
    private CameraPosition cameraPosition;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int markerNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            lastLocation = savedInstanceState.getParcelable("location");
            cameraPosition = savedInstanceState.getParcelable("camera_position");
        }
        setContentView(R.layout.activity_maps);
        //https://medium.com/@droidbyme/get-current-location-using-fusedlocationproviderclient-in-android-cb7ebf5ab88e
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView = findViewById(R.id.textView2);
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable("camera_position", mMap.getCameraPosition());
            outState.putParcelable("location", lastLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getLocationPermission();
        updateLocation();
        getDeviceLocation();

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(budynek_CTI).title("Centrum Technologii Informatycznych"));
        mMap.addMarker(new MarkerOptions().position(budynek_centrum_sportu).title("Centrum Sportu"));
        mMap.addMarker(new MarkerOptions().position(budynek_sukcesja).title("Galeria Sukcesja"));
        mMap.addMarker(new MarkerOptions().position(budynek_WEEIA).title("Wydzial EEIA"));
        /*mMap.moveCamera(CameraUpdateFactory.newLatLng(budynek_CTI));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(budynek_centrum_sportu));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(budynek_sukcesja));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(budynek_WEEIA));*/

        threadLocalization = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(250); //1000ms = 1 sec
                        runOnUiThread(new Runnable() {
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
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() == true) {
                            // Set the map's camera position to the current location of the device.
                            lastLocation = task.getResult();

                            latitude = lastLocation.getLatitude();
                            longitude = lastLocation.getLongitude();

                            if (!wasCentered) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(latitude, longitude), 17));
                                wasCentered = true;
                            }
                            textView.setText("Current location: " + latitude + " , " + longitude);

                            //addNewMarker(latitude, longitude, BitmapDescriptorFactory.fromResource(R.drawable.emoji));

                            markerNumber++;
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(budynek_CTI, 17));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void addNewMarker(double latitude, double longitude, BitmapDescriptor myIcon) {
        if (marker != null && markerNumber > 0)
            marker.remove();
        BitmapDescriptor icon = myIcon;
        LatLng other = new LatLng(latitude, longitude);
        marker = mMap.addMarker(new MarkerOptions().position(other).title("You're here").icon(icon));
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
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationPermissionGranted = true;

        } else {
            Log.d(TAG, "Current location is null");
            Log.e(TAG, "Exception: s");

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "permission was granted  :)", Toast.LENGTH_LONG).show();
                }
                updateLocation();
            }
        }
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocation() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);     //Twoja lokalizacja
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
