package politechnika.meetyourtrainer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import politechnika.meetyourtrainer.GoogleMaps.SearchMapView;

public class SearchPagerAdapter extends FragmentStatePagerAdapter {
    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       if(position == 0)
           return SearchMapView.newInstance();
       else
           return SearchListView.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public String getPageTitle(int position) {
        if(position == 1) return "List View";
        else return "Map View";
    }
}
