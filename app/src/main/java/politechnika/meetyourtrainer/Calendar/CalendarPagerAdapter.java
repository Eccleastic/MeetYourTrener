package politechnika.meetyourtrainer.Calendar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CalendarPagerAdapter extends FragmentStatePagerAdapter {
    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return CalendarThisMonthView.newInstance();
        if(position == 1)
            return CalendarThisWeekView.newInstance();
        else
            return CalendarPeriodTimeView.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public String getPageTitle(int position) {
        if(position == 0) return "This Month";
        else if (position == 1) return "This Week";
        else return "Period of time";
    }
}
