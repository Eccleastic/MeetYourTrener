package politechnika.meetyourtrainer.UserProfile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class UserProfilePagerAdapter extends FragmentStatePagerAdapter {

    public UserProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return UserProfileMyProfileView.newInstance();
        else
            return UserProfileEditProfileView.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public String getPageTitle(int position) {
        if (position == 0) return "My Profile";
        else return "Edit Profile";
    }
}
