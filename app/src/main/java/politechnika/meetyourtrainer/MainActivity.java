package politechnika.meetyourtrainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import politechnika.meetyourtrainer.Ads.FragmentAds;
import politechnika.meetyourtrainer.Calendar.FragmentCalendar;
import politechnika.meetyourtrainer.Chat.FragmentMessage;
import politechnika.meetyourtrainer.Search.FragmentSearch;
import politechnika.meetyourtrainer.UserProfile.FragmentUserProfile;

public class MainActivity extends AppCompatActivity {

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
                    case R.id.item_ads:
                        selectedFragment = new FragmentAds();
                        break;
                    case R.id.item_myprofile:
                        selectedFragment = new FragmentUserProfile();
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
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
