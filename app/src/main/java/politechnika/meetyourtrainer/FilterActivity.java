package politechnika.meetyourtrainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {

    Button saveButton, exitButton;
    EditText distance, text, maxdate, maxprice;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_ads);
        sharedPreferences = getSharedPreferences("FilterData", Context.MODE_PRIVATE);
        exitButton = findViewById(R.id.exitButton);
        saveButton = findViewById(R.id.saveButton);
        distance = findViewById(R.id.distance);
        text = findViewById(R.id.text);
        maxdate = findViewById(R.id.maxdate);
        maxprice = findViewById(R.id.maxprice);
        distance.setText(sharedPreferences.getString("distance", "10"));
        text.setText(sharedPreferences.getString("text", ""));
        maxdate.setText(sharedPreferences.getString("maxdate", "01.01.2023"));
        maxprice.setText(sharedPreferences.getString("maxprice", "999"));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("FilterData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("distance", String.valueOf(distance.getText()));
                editor.putString("text", String.valueOf(text.getText()));
                editor.putString("maxdate", String.valueOf(maxdate.getText()));
                editor.putString("maxprice", String.valueOf(maxprice.getText()));
                editor.apply();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
