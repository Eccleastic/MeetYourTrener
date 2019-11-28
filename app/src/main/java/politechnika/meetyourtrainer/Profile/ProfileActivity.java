package politechnika.meetyourtrainer.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import politechnika.meetyourtrainer.R;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName;
    TextView rating;
    TextView seeOpinions;
    TextView profileDescription;
    ImageView phoneButton;
    ImageView messageButton;
    Button backButton;

    String name;
    String rate = "3.1";
    String description;
    String phone = "224-444-444";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b = getIntent().getExtras();

        profileName = findViewById(R.id.profileName);
        rating = findViewById(R.id.rating);
        seeOpinions = findViewById(R.id.seeOpinions);
        profileDescription = findViewById(R.id.profileDescription);

        phoneButton = findViewById(R.id.phoneButton);
        messageButton = findViewById(R.id.messageButton);
        backButton = findViewById(R.id.backButton);

        if (getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("rate")) {
            profileName.setText(b.getString("title"));
            profileDescription.setText(b.getString("description"));
            rating.setText(String.format("%.2f", b.getDouble("rate")));
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

    }
}
