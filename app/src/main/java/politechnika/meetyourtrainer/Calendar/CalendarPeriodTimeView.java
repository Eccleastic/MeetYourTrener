package politechnika.meetyourtrainer.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import politechnika.meetyourtrainer.R;

public class CalendarPeriodTimeView extends Fragment {
    TextView dateStart;
    TextView dateEnd;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button submitButton;

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
        submitButton = view.findViewById(R.id.submitButton);

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
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                MeetingProvider mp = new MeetingProvider(dateStart.getText().toString(), dateEnd.getText().toString());
                mp.getDataFromApi(getActivity());
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        myAdapter = new MyAdapter(getActivity().getApplicationContext(), getList());
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    private ArrayList<CardModel> getList() {

        ArrayList<CardModel> models = new ArrayList<>();

        CardModel m = new CardModel();
        m.setDesctiption("Adam Malysz, 19:00 07.12.2019(sobota)");
        m.setTitle("Skoki narciarskie");
        m.setImg(R.drawable.face);
        models.add(m);

        m = new CardModel();
        m.setDesctiption("Michał Tybora, 12:00 09.12.2019(poniedziałek)");
        m.setTitle("Trójbój siłowy");
        m.setImg(R.drawable.emoji);
        models.add(m);

        m = new CardModel();
        m.setDesctiption("Joanna Jedrzejczyk, 20:00 10.12.2019(wtorek)");
        m.setTitle("Kickboxing");
        m.setImg(R.drawable.face);
        models.add(m);

        m = new CardModel();
        m.setDesctiption("Kasia Basia , 22:00 10.12.2019(wtorek)");
        m.setTitle("Yoga");
        m.setImg(R.drawable.emoji);
        models.add(m);

        m = new CardModel();
        m.setDesctiption("Otylia Jedrzejczak, 06:00 12.12.2019(czwartek)");
        m.setTitle("Basen");
        m.setImg(R.drawable.face);
        models.add(m);

        m = new CardModel();
        m.setDesctiption("Otylia Jedrzejczak, 20:00 12.12.2019(czwartej)");
        m.setTitle("Basen");
        m.setImg(R.drawable.emoji);
        models.add(m);

        return models;
    }
}
