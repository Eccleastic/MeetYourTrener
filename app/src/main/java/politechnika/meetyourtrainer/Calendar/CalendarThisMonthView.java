package politechnika.meetyourtrainer.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class CalendarThisMonthView extends Fragment {
    public static CalendarThisMonthView newInstance() {
        return new CalendarThisMonthView();
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
