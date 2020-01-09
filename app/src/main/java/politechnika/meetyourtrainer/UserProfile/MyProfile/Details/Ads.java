package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class Ads extends Fragment {

    public static Ads newInstance() {
        return new Ads();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
