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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import politechnika.meetyourtrainer.R;

public class CalendarThisWeekView extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public static CalendarThisWeekView newInstance() {
        return new CalendarThisWeekView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("CalendarThisWeekView onCreate");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar_week, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewWeek);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        String nextWeekDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(calendar.getTime());
        System.out.println("Next week date: " + nextWeekDate);
        
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");

        MeetingProvider mp = new MeetingProvider(currentDate, nextWeekDate);
        ArrayList<CardModel> models = new ArrayList<>();
        CardModel m = new CardModel();
        mp.getDataFromApi(getActivity(), new ServerCallback() {

            @Override
            public void onSuccess(JSONArray response) throws JSONException {
                if (response.length() == 0) {
                    m.setTitle("No meetings in this week");
                    m.setDesctiption("");
                    m.setImg(R.drawable.sademoji);
                    models.add(m);
                } else {
                    for (int i = 0; i < response.length(); i++) {
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
