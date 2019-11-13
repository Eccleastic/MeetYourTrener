package politechnika.meetyourtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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

    private boolean emailValidate() {
        boolean valid = true;

        String email = emailAddress.getText().toString();
        if (email.isEmpty()) {
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailAddress.setError("Enter a valid email address");
            valid = false;
        } else {
            emailAddress.setError(null);
        }

        return valid;
    }

    private boolean passwordValidate() {
        boolean valid = true;

        String password = userPassword.getText().toString();

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            userPassword.setError("Password between 4 and 10 characters");
            valid = false;
        } else {
            userPassword.setError(null);
        }


        return valid;
    }

    private void Login() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emailAddress.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter username or email", Toast.LENGTH_LONG).show();
                } else {
                    emailValidate();
                }
                if (userPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter password", Toast.LENGTH_LONG).show();
                } else {
                    passwordValidate();
                }
                String url = "jdbc:sqlserver://mytdbserver.database.windows.net:1433;database=MYTDB;user=admin1@mytdbserver;password=mytdbAdmin#;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

                try {
                    Connection connection = null;
                    connection = DriverManager.getConnection(url);
//                    String schema = connection.getSchema();
//                    System.out.println("Successful connection - Schema: " + schema);

                    System.out.println("Query data example:");
                    System.out.println("=========================================");

                    // Create and execute a SELECT SQL statement.
                    String selectSql = "SELECT * FROM Users";

                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(selectSql)) {

                        // Print results from select statement
                        System.out.println("Useres table:");
                        while (resultSet.next()) {
                            System.out.println(resultSet.getString(1)
                                    + " "
                                    + resultSet.getString(2)
                                    + " "
                                    + resultSet.getString(3)
                                    + " "
                                    + resultSet.getString(4));
                        }
                        connection.close();
                        if (resultSet.getString(4).equals(userPassword.getText().toString())) {
                            Toast.makeText(getBaseContext(), "Password is correct", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
