package politechnika.meetyourtrainer.UserProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import politechnika.meetyourtrainer.R;
import politechnika.meetyourtrainer.UserProfile.MyProfile.Details.DetailsAdapter;

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

        sex_spinner = view.findViewById(R.id.sex_spinner);
        user_settings_spinner = view.findViewById(R.id.user_settings_spinner);
        ArrayAdapter<CharSequence> sex_spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.sex_spinner, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> user_settings_spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.user_settings_spinner, android.R.layout.simple_spinner_item);
        sex_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_settings_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(sex_spinner_adapter);
        user_settings_spinner.setAdapter(user_settings_spinner_adapter);

        username = view.findViewById(R.id.username);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        surnameEditText = view.findViewById(R.id.surnameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);

        return view;
    }
}
