package politechnika.meetyourtrainer.Calendar;

import android.app.ProgressDialog;
import android.content.Intent;
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

import politechnika.meetyourtrainer.MeetingInfoActivity;
import politechnika.meetyourtrainer.Profile.ProfileActivity;
import politechnika.meetyourtrainer.R;

public class CalendarPeriodTimeView extends Fragment {
    TextView dateStart;
    TextView dateEnd;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button submitButton;

    String id, description, address;

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
        recyclerView = view.findViewById(R.id.recyclerView);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        dateStart.setText(currentDate);
        dateEnd.setText(currentDate);

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

                MeetingProvider mp = new MeetingProvider(dateStart.getText().toString(), dateEnd.getText().toString());
                ArrayList<CardModel> models = new ArrayList<>();
                mp.getDataFromApi(getActivity(), new ServerCallback() {

                    @Override
                    public void onSuccess(JSONArray response) throws JSONException {
                        if (response.length() == 0) {
                            CardModel m = new CardModel();
                            m.setTitle("No meetings planned");
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
