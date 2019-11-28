package politechnika.meetyourtrainer;
//
//import android.content.Context;
//import android.content.Intent;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentActivity;

//public class MainActivity extends FragmentActivity {
//
//    Button button1;
//    Context context;
//    TextView textView;
//    LocationManager locationManager;
//    boolean gpsStatus;
//    Intent intent1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.page);
//
//        textView = findViewById(R.id.textView);
//        context = getApplicationContext();
//        button1 = findViewById(R.id.button1);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                getGpsStatus();
//                intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//
//                if (gpsStatus == true) {
//
//                    startActivity(new Intent(context, MapsActivity.class));
//                } else {
//                    textView.setText("Location is disabled, Please turn location on ");
//                    startActivity(intent1);
//                }
//            }
//        });
//    }
//
//    public void getGpsStatus() {
//        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//    }
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_CODE = 1;
    Button filterButton;

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
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
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //ustawianie widoku mapy jako pierwsze co widzimy po zalogowaniu
        setFragment(new FragmentSearch());

        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
