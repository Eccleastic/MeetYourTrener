package politechnika.meetyourtrainer.UserProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class UserProfileMyProfileView extends Fragment {

    public static UserProfileMyProfileView newInstance() {
        return new UserProfileMyProfileView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
