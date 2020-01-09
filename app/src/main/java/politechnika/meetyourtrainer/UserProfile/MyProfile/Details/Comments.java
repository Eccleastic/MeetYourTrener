package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class Comments extends Fragment {

    public static Comments newInstance() {
        return new Comments();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
