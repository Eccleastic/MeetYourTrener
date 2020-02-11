package politechnika.meetyourtrainer.Calendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import politechnika.meetyourtrainer.R;

public class CalendarPeriodTimeView extends Fragment {
    TextView dateStart;
    TextView dateEnd;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button submitButton;

    String id, description, address;

    SharedPreferences sharedPreferences;

    public static CalendarPeriodTimeView newInstance() {
        return new CalendarPeriodTimeView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("CalendarPeriodTimeView onCreate");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar_period, container, false);
        dateStart = view.findViewById(R.id.dateStart);
        dateEnd = view.findViewById(R.id.dateEnd);
        submitButton = view.findViewById(R.id.submitButton);
        recyclerView = view.findViewById(R.id.recyclerViewPeriod);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        dateStart.setText(currentDate);
        dateEnd.setText(currentDate);
        sharedPreferences = getActivity().getSharedPreferences("UserCalendar", Context.MODE_PRIVATE);
        dateStart.setText(sharedPreferences.getString("CalendarDateStart", "01.01.2020"));
        dateEnd.setText(sharedPreferences.getString("CalendarDateEnd", "01.01.2021"));

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
                final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("CalendarDateStart", dateStart.getText().toString());
                editor.putString("CalendarDateEnd", dateEnd.getText().toString());
                editor.apply();
                String start, end;
                start = sharedPreferences.getString("CalendarDateStart", "01.01.2020");
                end = sharedPreferences.getString("CalendarDateEnd", "01.01.2021");

                MeetingProvider mp = new MeetingProvider();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String userid = sharedPreferences.getString("user_id", "1");
                ArrayList<CardModel> models = new ArrayList<>();
                CardModel m = new CardModel();
                mp.getMeetingsFromDateToDate(getActivity(),start, end, userid, new ServerCallback() {

                    @Override
                    public void onSuccess(JSONArray response) throws JSONException {
                        System.out.println(response);
                        if (response.length() == 0) {
                            m.setTitle("No meetings planned");
                            m.setDesctiption("");
                            m.setImg(R.drawable.sademoji);
                            models.add(m);
                        } else {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                m.setDesctiption(/* obj.getString("note") + ", " + */obj.getString("meeting_address") + ", " + obj.getString("meeting_date"));
                                m.setTitle("trener_ID: " + obj.getInt("trener_ID"));
                                m.setImg(R.drawable.face);
                                id = Integer.toString(obj.getInt("meeting_ID"));
                                address = obj.getString("meeting_address");
                                description = response.toString();
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
            }
        });

        return view;
    }
}
