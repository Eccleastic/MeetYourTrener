package politechnika.meetyourtrainer.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import politechnika.meetyourtrainer.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name;
    TextView rating;
    ImageView phone;
    ImageView message;
    ImageView ratePhoto;
    Button backButton;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b = getIntent().getExtras();

        name = findViewById(R.id.name);
        rating = findViewById(R.id.rate);
        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        backButton = findViewById(R.id.backButton);
        viewPager = findViewById(R.id.pager);

        /*
        if (getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("rate")) {
            name.setText(b.getString("title"));
            profileDescription.setText(b.getString("description"));
            rating.setText(String.format("%.2f", b.getDouble("rate")));
        }
         */

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

    }
}
