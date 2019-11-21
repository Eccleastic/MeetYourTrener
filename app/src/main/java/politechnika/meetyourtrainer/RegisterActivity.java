package politechnika.meetyourtrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import politechnika.meetyourtrainer.api.APIHandler;

public class RegisterActivity extends AppCompatActivity {
    EditText userName, userPassword;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userNameEditText);
        userPassword = findViewById(R.id.userPasswordEditText);

        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().length() > 0 && userName.getText().toString().length() < 50 ){
                    if(userPassword.getText().toString().length() > 4 && userPassword.getText().toString().length() < 36){
                        APIHandler.registerNewUser(userName.getText().toString(), userPassword.getText().toString());
                    }
                }
            }
        });

    }

    private void singUp() {

    }

}
