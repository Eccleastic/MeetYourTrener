package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class AboutMe extends Fragment {

    public static AboutMe newInstance() {
        return new AboutMe();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
