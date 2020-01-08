package politechnika.meetyourtrainer.UserProfile.MyProfile;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import politechnika.meetyourtrainer.AdInfoActivity;
import politechnika.meetyourtrainer.Profile.ProfileProvider;
import politechnika.meetyourtrainer.Profile.ServerCallback;
import politechnika.meetyourtrainer.R;
import politechnika.meetyourtrainer.UserProfile.MyProfile.Details.DetailsAdapter;

public class UserProfileMyProfileView extends Fragment {

    private static final int REQUEST_CALL = 1;

    TextView name;
    TextView rating;
    ImageView image;
    ImageView phone;
    ImageView message;
    ImageView ratePhoto;
    Button backButton;
    ViewPager viewPager;
    SharedPreferences result;
    String user_id;
    String email, phoneNumber, description, realName;
    int age, sex;
    DetailsAdapter detailsAdapter;

    public static UserProfileMyProfileView newInstance() {
        return new UserProfileMyProfileView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile, container, false);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        rating = view.findViewById(R.id.rate);
        phone = view.findViewById(R.id.phone);
        message = view.findViewById(R.id.message);
        backButton = view.findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
        result = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = result.getString("user_id", "-1");
        ProfileProvider profileProvider = new ProfileProvider();
        profileProvider.getProfileById(getActivity(), user_id, new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                if (result.length() > 0){
                    name.setText(result.getString("trener_name").trim());
                    rating.setText(result.getString("user_rating"));
                    phoneNumber = result.getString("contact_phone").trim();
                    email = result.getString("contact_email").trim();
                    description = result.getString("trener_description");
                    setBitmapFromURL(result.getString("photo_link"), image);
                    realName = result.getString("first_name") + " " + result.getString("last_name");
                    age = result.getInt("age");
                    sex = result.getInt("sex");
                }
                dialog.dismiss();
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED PHONE");
                if(phoneNumber.length() >= 7) {
                    makePhoneCall();
                } else {
                    Toast.makeText(getActivity(), "No phone number", Toast.LENGTH_SHORT);
                }
            }
        });
        System.out.println(realName + age + sex + email + phoneNumber);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.pagerProfile);
        detailsAdapter = new DetailsAdapter(getChildFragmentManager());
        viewPager.setAdapter(detailsAdapter);
    }

    public void setBitmapFromURL(String url, ImageView img){
        String drawableRes= url;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url1 = new URL(drawableRes);
            img.setImageBitmap(BitmapFactory.decodeStream((InputStream)url1.getContent()));
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
    }

    public void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + phoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT);
            }
        }
    }
}
