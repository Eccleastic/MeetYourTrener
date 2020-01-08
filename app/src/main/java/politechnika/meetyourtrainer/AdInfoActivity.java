package politechnika.meetyourtrainer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AdInfoActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    ImageView userPhoto, directions, phone, message;
    TextView name;
    TextView rate;
    TextView title;
    TextView price;
    TextView description;
    TextView address;
    Button backButton;

    String phoneNumber;

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

        if (getIntent().hasExtra("name")) {
            name.setText(b.getString("name"));
            rate.setText(b.getString("rate"));
            title.setText(b.getString("title"));
            price.setText(b.getString("price") + " zł");
            description.setText(b.getString("description") +"\n Data: "+ b.getString("date") + " Kontakt: " + b.getString("email") + " " + b.getString("phone"));
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

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED PHONE");
                String number = b.getString("phone").trim();
                System.out.println(number);
                if(number.length() >= 7) {
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
                
            }
        });

    }

    public void setBitmapFromURL(String url, ImageView img){
        String drawableRes= url;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url1 = new URL(drawableRes);
            img.setImageBitmap(BitmapFactory.decodeStream((InputStream)url1.getContent()));
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
    }

    public void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(AdInfoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AdInfoActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + phoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            } else {
                Toast.makeText(AdInfoActivity.this, "Permission DENIED", Toast.LENGTH_SHORT);
            }
        }
    }
}
