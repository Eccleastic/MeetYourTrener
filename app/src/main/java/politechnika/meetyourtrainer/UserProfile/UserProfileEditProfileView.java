package politechnika.meetyourtrainer.UserProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class UserProfileEditProfileView extends Fragment {
    public static UserProfileEditProfileView newInstance() {
        return new UserProfileEditProfileView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
