//package politechnika.meetyourtrainer;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class SignUpPage extends AppCompatActivity {
//
//    Button i_am_a_trainer;
//    Button looking_for_trainer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up_page);
//
//        i_am_a_trainer = (Button) findViewById(R.id.button2);
//        looking_for_trainer = (Button) findViewById(R.id.button3);
//
//        MainMenu();
//        map();
//    }
//
//    ;
//
//    private void MainMenu() {
//        i_am_a_trainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SignUpPage.this, MainActivity.class));
//            }
//        });
//    }
//
//    private void map() {
//        looking_for_trainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SignUpPage.this, MapsActivity.class));
//            }
//        });
//    }
//
//}
package politechnika.meetyourtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {

    Button iAmTrenerButton, lookingForTrenerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        iAmTrenerButton = (Button) findViewById(R.id.button2);
        lookingForTrenerButton = findViewById(R.id.button3);


        SingUpType();
    }


    private void SingUpType() {
        iAmTrenerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPage.this, RegisterActivity.class));
            }
        });

        lookingForTrenerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this, RegisterActivity.class));
            }
        });
    }

}

