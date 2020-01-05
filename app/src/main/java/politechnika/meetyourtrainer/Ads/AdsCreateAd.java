package politechnika.meetyourtrainer.Ads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import politechnika.meetyourtrainer.R;

public class AdsCreateAd extends Fragment {

    RecyclerView recyclerView;

    public static AdsCreateAd newInstance() {
        return new AdsCreateAd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_createad, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCreateAd);

        return view;
    }
}
