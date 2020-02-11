package politechnika.meetyourtrainer.Ads;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import politechnika.meetyourtrainer.Calendar.MeetingProvider;
import politechnika.meetyourtrainer.LoginPage;
import politechnika.meetyourtrainer.MainActivity;
import politechnika.meetyourtrainer.R;

public class AdInfoActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    ImageView userPhoto, directions, phone, message;
    TextView name;
    TextView rate;
    TextView title;
    TextView price;
    TextView description;
    TextView address;
    Button backButton, takePartButton;

    String phoneNumber;
    String userId;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        Bundle b = getIntent().getExtras();

        userPhoto = findViewById(R.id.image);
        directions = findViewById(R.id.directions);
        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        name = findViewById(R.id.name);
        rate = findViewById(R.id.rate);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        address = findViewById(R.id.address);
        backButton = findViewById(R.id.backButton);
        takePartButton = findViewById(R.id.takePartButton);
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", "-1");
        userId = sharedPreferences.getString("user_id", "-1");

        if (getIntent().hasExtra("name")) {
            if (b.get("id").toString().trim().equals(userId)) {
                takePartButton.setText("Edit Ad");
            }
            name.setText(b.getString("name"));
            rate.setText(b.getString("rate"));
            title.setText(b.getString("title"));
            price.setText(b.getString("price") + " zł");
            description.setText(b.getString("description") + "\n Data: " + b.getString("date") + " Kontakt: " + b.getString("email") + " " + b.getString("phone"));
            address.setText(b.getString("address"));
            setBitmapFromURL(b.getString("photoURL"), userPhoto);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

        takePartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to edit ad activity
                if (getIntent().hasExtra("ad_id")) {
                    final ProgressDialog dialog = ProgressDialog.show(AdInfoActivity.this, null, "Please wait");
                    AdInfoProvider adInfoProvider = new AdInfoProvider();
                    adInfoProvider.getAdByAdId(getApplicationContext(), b.getString("ad_id"), new ServerCallbackTwo() {
                        @Override
                        public void onSuccess(JSONObject result1) throws JSONException {
                            Bundle bundlesForEditView = new Bundle();
                            bundlesForEditView.putString("latitude", result1.getString("latitude"));
                            bundlesForEditView.putString("longitude", result1.getString("longitude"));
                            bundlesForEditView.putString("title", result1.getString("title"));
                            bundlesForEditView.putString("price", result1.getString("price"));
                            bundlesForEditView.putString("date", result1.getString("date"));
                            bundlesForEditView.putString("time", result1.getString("time"));
                            bundlesForEditView.putString("address", result1.getString("address"));
                            bundlesForEditView.putString("ad_description", result1.getString("ad_description"));
                            bundlesForEditView.putString("advertisement_id", result1.getString("advertisement_id"));
                            bundlesForEditView.putString("trener_id", result1.getString("trener_id"));

                            if (takePartButton.getText().equals("Edit Ad")) {
                                Intent intent = new Intent(getApplicationContext(), EditAdActivity.class);
                                intent.putExtras(bundlesForEditView);
                                startActivity(intent);
                                dialog.dismiss();
                            } else {
                                MeetingProvider meetingProvider = new MeetingProvider();
                                meetingProvider.createMeeting(
                                        getApplicationContext(),
                                        b.getString("ad_id"),
                                        userId,
                                        result1.getString("trener_id"),
                                        result1.getString("date") + " " + result1.getString("time"),
                                        result1.getString("address"),
                                        result1.getString("latitude"),
                                        result1.getString("longitude"),
                                        result1.getString("price"),
                                        result1.getString("title"),
                                        new ServerCallbackTwo() {
                                            @Override
                                            public void onSuccess(JSONObject result) throws JSONException {
                                                System.out.println(result);
                                                if (result.getString("wasAdded").equals("true")) {
                                                    System.out.println("Added meeting");
                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                }
                                            }
                                        }
                                );
                            }
                        }
                    });
                }

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED PHONE");
                String number = b.getString("phone").trim();
                System.out.println(number);
                if (number.length() >= 7) {
                    phoneNumber = number;
                    makePhoneCall();
                } else {
                    Toast.makeText(AdInfoActivity.this, "No phone number", Toast.LENGTH_SHORT);
                }
            }
        });

        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to profile
            }
        });

    }

    public void setBitmapFromURL(String url, ImageView img) {
        String drawableRes = url;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url1 = new URL(drawableRes);
            img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url1.getContent()));
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
    }

    public void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(AdInfoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AdInfoActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + phoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(AdInfoActivity.this, "Permission DENIED", Toast.LENGTH_SHORT);
            }
        }
    }
}
