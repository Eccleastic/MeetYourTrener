package politechnika.meetyourtrainer.Calendar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");

        MeetingProvider mp = new MeetingProvider(currentDate, currentDate);
        ArrayList<CardModel> models = new ArrayList<>();
        mp.getDataFromApi(getActivity(), new ServerCallback() {

            @Override
            public void onSuccess(JSONArray response) throws JSONException {
                if (response.length() == 0) {
                    CardModel m = new CardModel();
                    m.setTitle("No meetings today");
                    m.setDesctiption("");
                    m.setImg(R.drawable.sademoji);
                    models.add(m);
                } else {
                    for (int i = 0; i < response.length(); i++) {
                        CardModel m = new CardModel();
                        JSONObject obj = response.getJSONObject(i);
                        m.setDesctiption(obj.getString("note") + ", " + obj.getString("meeting_address") + ", " + obj.getString("meeting_date"));
                        m.setTitle("trener_ID: " + obj.getInt("trener_ID"));
                        m.setImg(R.drawable.face);
                        models.add(m);
                        System.out.println(response.getJSONObject(i).toString());
                    }
                }
                myAdapter = new MyAdapter(getActivity(), models);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(myAdapter);
                dialog.dismiss();
            }
        });

        return view;
    }
} 
