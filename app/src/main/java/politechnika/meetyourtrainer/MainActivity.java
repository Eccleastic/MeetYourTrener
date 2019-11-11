package politechnika.meetyourtrainer;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    Button button1;
    Context context;
    TextView textView;
    LocationManager locationManager;
    boolean gpsStatus;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        textView = findViewById(R.id.textView);
        context = getApplicationContext();
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getGpsStatus();
                intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                if (gpsStatus == true) {

                    startActivity(new Intent(context, MapsActivity.class));
                } else {
                    textView.setText("Location is disabled, Please turn location on ");
                    startActivity(intent1);
                }
            }
        });
    }

    public void getGpsStatus() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.item_search:
                            selectedFragment = new FragmentSearch();
                            break;
                        case R.id.item_messages:
                            selectedFragment = new FragmentMessage();
                            break;
                        case R.id.item_calendar:
                            selectedFragment = new FragmentCalendar();
                            break;
                        case R.id.item_contacts:
                            selectedFragment = new FragmentContact();
                            break;
                        case R.id.item_settings:
                            selectedFragment = new FragmentSettings();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}
