package politechnika.meetyourtrainer.Search;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import politechnika.meetyourtrainer.Ads.AdInfoProvider;
import politechnika.meetyourtrainer.Ads.CardModel;
import politechnika.meetyourtrainer.Ads.MyAdapter;
import politechnika.meetyourtrainer.R;

public class SearchListView extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    private TextView name;
    private TextView description;
    private TextView name2;
    private TextView description2;

    private TextView user1profile;
    private TextView user2profile;

    SharedPreferences sharedPreferences;


    public static SearchListView newInstance() {
        return new SearchListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ads, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAds);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
        AdInfoProvider ads = new AdInfoProvider();
        sharedPreferences = this.getActivity().getSharedPreferences("FilterData", Context.MODE_PRIVATE);
        String latitude, longitude, distance, maxdate, maxprice;
        latitude = sharedPreferences.getString("latitude", "51");
        longitude = sharedPreferences.getString("longitude", "19");
        distance = sharedPreferences.getString("distance", "10");
        maxdate = sharedPreferences.getString("maxdate", "01.01.2023");
        maxprice = sharedPreferences.getString("maxprice", "999.00");
        ArrayList<CardModel> models = new ArrayList<>();

        ads.getAdByFilters(getActivity(),latitude,longitude,distance,maxdate,maxprice, new politechnika.meetyourtrainer.Ads.ServerCallback() {
            @Override
            public void onSuccess(JSONArray result) throws JSONException {
                if (result.length() == 0) {
                    CardModel m = new CardModel();
                    m.setTitle("There is no ads with specified filters");
                    m.setDescription("");
                    m.setImgURL("");
                    models.add(m);
                } else {
                    for(int i=0; i<result.length(); i++) {
                        CardModel m = new CardModel();
                        JSONObject obj = result.getJSONObject(i);
                        m.setDescription(obj.getString("ad_description"));
                        m.setTitle(obj.getString("title"));
                        m.setImgURL(obj.getString("photo_link"));
                        m.setAd_id(obj.getString("advertisement_id"));
                        m.setAddress(obj.getString("address"));
                        m.setName(obj.getString("trener_name"));
                        m.setPrice(String.valueOf(obj.getDouble("price")));
                        m.setTrener_id(String.valueOf(obj.getInt("trener_id")));
                        m.setEmail(obj.getString("trener_email"));
                        m.setPhone(obj.getString("trener_phone"));
                        m.setDate(obj.getString("date") + " " + obj.getString("time"));
                        models.add(m);
                        System.out.println(result.get(i).toString());
                    }

                }
                myAdapter = new MyAdapter(getActivity(), models);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(myAdapter);
                dialog.dismiss();
            }
        });

        /*
        name = view.findViewById(R.id.user1name);
        description = view.findViewById(R.id.user1description);
        name2 = view.findViewById(R.id.user2name);
        description2 = view.findViewById(R.id.user2description);

        user1profile = view.findViewById(R.id.user1profile);
        user2profile = view.findViewById(R.id.user2profile);

        name.setText("Adam Malysz");
        description.setText("Specjalizuje sie w skakaniu. W góre, w bok, na nartach, bez nart, z jedną nartą, na wszystkim i ze wszystkim. 60$/h.");
        name2.setText("Kasia Drąg");
        description2.setText("Trenerka yogi z 15-letnim doświadczeniem. Zapraszam do siebie wszystkie panie od pon do pt w godzinach 8-14. 30$/h");

        user1profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        user2profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                ProfileProvider profile = new ProfileProvider("testtrener");
                profile.getDataFromApi(getActivity(), new ServerCallback() {
                    @Override
                    public void onSuccess(JSONObject response) throws JSONException {
                        profile.setUserId(response.getString("user_ID"));
                        profile.setName(response.getString("user_name"));
                        profile.setSex(Integer.valueOf(response.getString("sex")));
                        profile.setEmail(response.getString("contact_email"));
                        profile.setPhoneNumber(response.getString("contact_phone"));
                        profile.setName(response.getString("first_name"));
                        profile.setLastname(response.getString("last_name"));
                        profile.setRating(Double.valueOf(response.getString("user_rating")));
                        profile.setDescription(response.getString("description"));
                        System.out.println(response.getString("description"));
                        intent.putExtra("description", profile.getDescription());
                        intent.putExtra("title", profile.getName()+ " " + profile.getLastname());
                        intent.putExtra("rate", profile.getRating());
                        intent.putExtra("email", profile.getEmail());
                        intent.putExtra("phone", profile.getPhoneNumber());
                        startActivity(intent);
                    }
                });
            }
        });*/

        return view;
    }
}
