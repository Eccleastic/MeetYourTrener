package politechnika.meetyourtrainer.Ads;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import politechnika.meetyourtrainer.MainActivity;
import politechnika.meetyourtrainer.R;

public class AdsCreateAd extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    Button createAdButton, loadLocationButton;
    EditText latitudeEditText, longitudeEditText, titleEditText, dateEditText, priceEditText, descriptionEditText, timeEditText, addressEditText;

    SharedPreferences sharedPreferences;

    public static AdsCreateAd newInstance() {
        return new AdsCreateAd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_ad, container, false);
        initializeXmlComponents(view);
        //if(){

        //}
        //fillFieldsForEditView();
        sharedPreferences = this.getActivity().getSharedPreferences("FilterData", Context.MODE_PRIVATE);
        String latitude, longitude, userid;
        latitude = sharedPreferences.getString("latitude", "51");
        longitude = sharedPreferences.getString("longitude", "19");
        sharedPreferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id", "1");
        AdInfoProvider ads = new AdInfoProvider();

        createAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                Toast.makeText(getActivity(), "Successfully created ad!", Toast.LENGTH_LONG);
                final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
                ads.createNewAd(getActivity(), latitudeEditText.getText().toString(), longitudeEditText.getText().toString(), titleEditText.getText().toString(), userid, dateEditText.getText().toString(), priceEditText.getText().toString(), descriptionEditText.getText().toString(), timeEditText.getText().toString(), addressEditText.getText().toString(), new ServerCallbackTwo() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        dialog.dismiss();
                        startActivity(intent);
                    }
                });
            }
        });

        loadLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitudeEditText.setText(latitude);
                longitudeEditText.setText(longitude);
            }
        });

        return view;
    }

    private void initializeXmlComponents(View view) {
        createAdButton = view.findViewById(R.id.createAdButton);
        loadLocationButton = view.findViewById(R.id.loadLocationButton);
        latitudeEditText = view.findViewById(R.id.latitudeEditText);
        longitudeEditText = view.findViewById(R.id.longitudeEditText);
        titleEditText = view.findViewById(R.id.titleEditText);
        dateEditText = view.findViewById(R.id.dateEditText);
        priceEditText = view.findViewById(R.id.priceEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        timeEditText = view.findViewById(R.id.timeEditText);
        addressEditText = view.findViewById(R.id.addressEditText);
    }

}
