package politechnika.meetyourtrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeetingInfoActivity extends AppCompatActivity {
    TextView meetingID;
    TextView address;
    TextView setRoute;
    TextView meetingDescription;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_info);
        Bundle b = getIntent().getExtras();

        meetingID = findViewById(R.id.meetingID);
        address = findViewById(R.id.address);
        setRoute = findViewById(R.id.setRoute);
        meetingDescription = findViewById(R.id.meetingDescription);

        backButton = findViewById(R.id.backButton);

        if (getIntent().hasExtra("id") && getIntent().hasExtra("description") && getIntent().hasExtra("address")) {
            meetingID.setText(b.getString("id"));
            address.setText(b.getString("address"));
            meetingDescription.setText(b.getString("description"));
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
