package politechnika.meetyourtrainer.Ads;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class AdsMyAds extends Fragment {

    public static AdsMyAds newInstance() {
        return new AdsMyAds();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
