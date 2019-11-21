package politechnika.meetyourtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import politechnika.meetyourtrainer.api.APIHandler;


public class LoginPage extends AppCompatActivity {

    EditText emailAddress;
    EditText userPassword;
    Button loginButton;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginButton = (Button) findViewById(R.id.button_log_in);
        signInButton = (Button) findViewById(R.id.button_sign_in);
        userPassword = (EditText) findViewById(R.id.editText_password);
        emailAddress = (EditText) findViewById(R.id.editText_login);

        Login();

        SignIn();

    }

    private boolean validateLoginCredentials(String userName, String userPassword) {
        boolean valid = false;
        if (userPassword.isEmpty() || userPassword.length() < 4 || userPassword.length() > 10) {
            this.userPassword.setError("Password between 4 and 10 characters");
            return valid;
        } else {
            valid = APIHandler.confirUserNameAndUserPasswordWithDatabase(userName, userPassword);
            return valid;
        }
    }

    private void Login() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        if (emailAddress.getText().toString().trim().isEmpty() || userPassword.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(getBaseContext(), "Please enter login credentials", Toast.LENGTH_LONG).show();
                        } else {
                            if (validateLoginCredentials(emailAddress.getText().toString(), userPassword.getText().toString())) {
                                startActivity(new Intent(LoginPage.this, MainActivity.class));
                            }
                        }
                    }
                };
                thread.start();
            }
        });


    }

    private void SignIn() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, SignUpPage.class));
            }
        });

    }
}
