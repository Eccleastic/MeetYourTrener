package politechnika.meetyourtrainer.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import politechnika.meetyourtrainer.FilterActivity;
import politechnika.meetyourtrainer.R;
import politechnika.meetyourtrainer.Search.SearchPagerAdapter;


public class FragmentSearch extends Fragment {
    SearchPagerAdapter searchPagerAdapter;
    ViewPager viewPager;
    Button filterButton;
    private static final int ACTIVITY_REQUEST_CODE = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        searchPagerAdapter = new SearchPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(searchPagerAdapter);

        filterButton = view.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FilterActivity.class);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            }
        });

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