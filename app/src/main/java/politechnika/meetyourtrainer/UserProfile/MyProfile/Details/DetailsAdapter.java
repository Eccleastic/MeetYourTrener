package politechnika.meetyourtrainer.UserProfile.MyProfile.Details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DetailsAdapter extends FragmentStatePagerAdapter {

    public DetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return AboutMe.newInstance();
        else
            return Comments.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public String getPageTitle(int position) {
        if (position == 0) return "About me";
        else return "Comments";
    }
}
