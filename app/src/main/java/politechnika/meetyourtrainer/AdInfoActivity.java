package politechnika.meetyourtrainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AdInfoActivity extends AppCompatActivity {

    ImageView userPhoto;
    TextView name;
    TextView rate;
    TextView title;
    TextView price;
    TextView description;
    TextView address;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        Bundle b = getIntent().getExtras();

        userPhoto = findViewById(R.id.image);
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
}
