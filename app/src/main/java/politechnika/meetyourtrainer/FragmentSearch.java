package politechnika.meetyourtrainer;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


public class FragmentSearch extends Fragment {
    SearchPagerAdapter searchPagerAdapter;
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        searchPagerAdapter = new SearchPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(searchPagerAdapter);
    }

    /*
    Button button1;
    Context context;
    TextView textView;
    LocationManager locationManager;
    boolean gpsStatus;
    Intent intent1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    */
}
