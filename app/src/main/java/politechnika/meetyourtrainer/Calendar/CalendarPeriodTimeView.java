package politechnika.meetyourtrainer.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class CalendarPeriodTimeView extends Fragment {
    public static CalendarPeriodTimeView newInstance() {
        return new CalendarPeriodTimeView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        return view;
    }
}
