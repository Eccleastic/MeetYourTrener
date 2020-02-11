package politechnika.meetyourtrainer.Ads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import politechnika.meetyourtrainer.MainActivity;
import politechnika.meetyourtrainer.R;

public class EditAdActivity extends AppCompatActivity {
    Button editAdButton, loadLocationButton;
    EditText latitudeEditText, longitudeEditText, titleEditText, dateEditText, priceEditText, descriptionEditText, timeEditText, addressEditText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_ad);

        initializeXmlComponents();
        sharedPreferences = this.getSharedPreferences("FilterData", this.MODE_PRIVATE);
        String latitude, longitude, userid;
        latitude = sharedPreferences.getString("latitude", "51");
        longitude = sharedPreferences.getString("longitude", "19");
        sharedPreferences = this.getSharedPreferences("UserData", this.MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id", "1");
        Bundle b = getIntent().getExtras();
        if (!b.isEmpty()) {
            fillFieldsForEditView(b);
        }

        editAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                final ProgressDialog dialog = ProgressDialog.show(EditAdActivity.this, null, "Please Wait");
                AdInfoProvider adInfoProvider = new AdInfoProvider();
                adInfoProvider.editAd(
                        getApplicationContext(),
                        b.getString("advertisement_id"),
                        b.getString("latitude"),
                        b.getString("longitude"),
                        b.getString("title"),
                        b.getString("trener_id"),
                        b.getString("date"),
                        b.getString("time"),
                        b.getString("price"),
                        b.getString("address"),
                        b.getString("ad_description"),
                        new ServerCallbackTwo() {
                            @Override
                            public void onSuccess(JSONObject result) throws JSONException {
                                if (result.getString("wasEdited").equals("true")) {
                                    System.out.println(result.getString("wasEdited"));
                                    startActivity(intent);
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Successfully edited ad!", Toast.LENGTH_LONG);
                                }
                            }
                        }
                );

            }
        });

        loadLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitudeEditText.setText(latitude);
                longitudeEditText.setText(longitude);
            }
        });
    }

    private void initializeXmlComponents() {
        editAdButton = findViewById(R.id.createAdButton);
        editAdButton.setText("Edit Ad");
        loadLocationButton = findViewById(R.id.loadLocationButton);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        titleEditText = findViewById(R.id.titleEditText);
        dateEditText = findViewById(R.id.dateEditText);
        priceEditText = findViewById(R.id.priceEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        timeEditText = findViewById(R.id.timeEditText);
        addressEditText = findViewById(R.id.addressEditText);
    }

    public void fillFieldsForEditView(Bundle b) {
        latitudeEditText.setText(b.getString("latitude"));
        longitudeEditText.setText(b.getString("longitude"));
        titleEditText.setText(b.getString("title"));
        priceEditText.setText(b.getString("price"));
        dateEditText.setText(b.getString("date"));
        timeEditText.setText(b.getString("time"));
        addressEditText.setText(b.getString("address"));
        descriptionEditText.setText(b.getString("ad_description"));
    }
}
