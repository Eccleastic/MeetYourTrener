package politechnika.meetyourtrainer.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import politechnika.meetyourtrainer.R;

public class CalendarDayView extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public static CalendarDayView newInstance() {
        return new CalendarDayView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar_day, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        myAdapter = new MyAdapter(getActivity().getApplicationContext(), getList());
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    private ArrayList<CardModel> getList(){

        ArrayList<CardModel> models = new ArrayList<>();

        CardModel m = new CardModel();
        m.setDesctiption("Adam Malysz, 19:00 07.12.2019(sobota)");
        m.setTitle("Skoki narciarskie");
        m.setImg(R.drawable.face);
        models.add(m);

        return models;

    }
} 
