package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class Comments extends Fragment {

    public static Comments newInstance() {
        return new Comments();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        return view;
    }
}
