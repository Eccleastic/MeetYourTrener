package politechnika.meetyourtrainer.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import politechnika.meetyourtrainer.FilterActivity;
import politechnika.meetyourtrainer.R;
import politechnika.meetyourtrainer.Search.SearchPagerAdapter;


public class FragmentSearch extends Fragment {
    SearchPagerAdapter searchPagerAdapter;
    ViewPager viewPager;
    Button filterButton;
    private static final int ACTIVITY_REQUEST_CODE = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        searchPagerAdapter = new SearchPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(searchPagerAdapter);

        filterButton = view.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FilterActivity.class);
                startActivity(intent);
            }
        });

    }
}
