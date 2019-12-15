package politechnika.meetyourtrainer.Ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import politechnika.meetyourtrainer.R;
import politechnika.meetyourtrainer.UserProfile.UserProfilePagerAdapter;

public class FragmentAds extends Fragment {
    AdsPagerAdapter adsPagerAdaper;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adsPagerAdaper = new AdsPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adsPagerAdaper);
    }
}
