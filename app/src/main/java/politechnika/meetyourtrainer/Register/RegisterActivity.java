package politechnika.meetyourtrainer.Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import politechnika.meetyourtrainer.R;

public class RegisterActivity extends AppCompatActivity {
    EditText userName, userPassword, userPassword2;
    Button confirmButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userNameEditText);
        userPassword = findViewById(R.id.userPasswordEditText);
        userPassword2 = findViewById(R.id.userPasswordEditText2);

        confirmButton = findViewById(R.id.confirmButton);
        backButton = findViewById(R.id.backButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.length() > 3 && (userPassword.getText().toString().equals(userPassword2.getText().toString()))) {
                    RegisterUserProvider registerUserProvider = new RegisterUserProvider();
                    registerUserProvider.registerUser(getBaseContext(), userName.getText().toString(), userPassword.getText().toString());
                    setResult(Activity.RESULT_OK, new Intent());
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Login too short or passwords are incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

    }

}
