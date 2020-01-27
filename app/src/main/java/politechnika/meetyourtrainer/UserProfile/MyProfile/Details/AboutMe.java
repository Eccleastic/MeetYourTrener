package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class AboutMe extends Fragment {

    public static AboutMe newInstance() {
        return new AboutMe();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_me, container, false);
        return view;
    }
}
