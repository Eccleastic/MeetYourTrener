package politechnika.meetyourtrainer.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class CalendarPeriodTimeView extends Fragment {
    TextView dateStart;
    TextView dateEnd;

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

        View view = inflater.inflate(R.layout.fragment_calendar_period, container, false);
        dateStart = view.findViewById(R.id.dateStart);
        dateEnd = view.findViewById(R.id.dateEnd);

        dateStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateStartFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
        dateEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateEndFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        return view;
    }
}
