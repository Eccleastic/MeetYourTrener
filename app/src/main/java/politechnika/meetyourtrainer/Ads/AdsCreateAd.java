package politechnika.meetyourtrainer.Ads;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class AdsCreateAd extends Fragment {

    public static AdsCreateAd newInstance() {
        return new AdsCreateAd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
