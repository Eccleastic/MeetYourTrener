package politechnika.meetyourtrainer.UserProfile.EditProfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import politechnika.meetyourtrainer.Profile.ProfileProvider;
import politechnika.meetyourtrainer.Profile.ServerCallback;
import politechnika.meetyourtrainer.R;

public class UserProfileEditProfileView extends Fragment {

    TextView username;
    EditText firstNameEditText;
    EditText surnameEditText;
    EditText emailEditText;
    EditText phoneEditText;
    EditText ageEditText;
    EditText descriptionEditText;
    Spinner sex_spinner;
    Spinner user_settings_spinner;
    Button editProfileInfoButton;

    SharedPreferences result;

    public static UserProfileEditProfileView newInstance() {
        return new UserProfileEditProfileView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        //setting spinners
        sex_spinner = view.findViewById(R.id.sex_spinner);
        user_settings_spinner = view.findViewById(R.id.user_settings_spinner);
        ArrayAdapter<CharSequence> sex_spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.sex_spinner, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> user_settings_spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.user_settings_spinner, android.R.layout.simple_spinner_item);
        sex_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_settings_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(sex_spinner_adapter);
        user_settings_spinner.setAdapter(user_settings_spinner_adapter);

        //settings rest of components
        username = view.findViewById(R.id.username);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        surnameEditText = view.findViewById(R.id.surnameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        username = view.findViewById(R.id.username);
        editProfileInfoButton = view.findViewById(R.id.editProfileInfoButton);

        //filling fields with current user data
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
        result = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_id = result.getString("user_id", "-1");
        ProfileProvider profileProvider = new ProfileProvider();
        profileProvider.getProfileById(getActivity(), user_id, new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                if (result.length() > 0) {
                    username.setText(result.getString("trener_name").trim());
                    phoneEditText.setText(result.getString("contact_phone").trim());
                    emailEditText.setText(result.getString("contact_email").trim());
                    descriptionEditText.setText(result.getString("trener_description"));
                    firstNameEditText.setText(result.getString("first_name"));
                    surnameEditText.setText(result.getString("last_name"));
                    ageEditText.setText(String.valueOf(result.getInt("age")));
                    sex_spinner.setSelection(result.getInt("sex") == 1 ? 0 : 1);
                }
                dialog.dismiss();
            }
        });

        //accept user data changes
        editProfileInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id, password, sex, age, email, phone, firstname, lastname, description;
                id = result.getString("user_id", "-1");
                password = result.getString("user_password", "");
                sex = String.valueOf(sex_spinner.getSelectedItemPosition()+1);
                age = ageEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                firstname = firstNameEditText.getText().toString();
                lastname = surnameEditText.getText().toString();
                description = descriptionEditText.getText().toString();


                final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
                profileProvider.editUserProfileInfo(getActivity(), id, password, sex, age, email, phone, firstname, lastname, description, new ServerCallback() {
                    @Override
                    public void onSuccess(JSONObject result) throws JSONException {
                        if (result.getBoolean("wasEdited")) {
                            Toast.makeText(getActivity(), "Profile information successfully changed!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });


        return view;
    }
}
