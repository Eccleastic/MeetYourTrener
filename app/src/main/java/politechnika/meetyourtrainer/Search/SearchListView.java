package politechnika.meetyourtrainer.Search;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    SharedPreferences sharedPreferences;


    public static SearchListView newInstance() {
        return new SearchListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("SearchListView onCreate");
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

        ads.getAdByFilters(getActivity(), latitude, longitude, distance, maxdate, maxprice, new politechnika.meetyourtrainer.Ads.ServerCallback() {
            @Override
            public void onSuccess(JSONArray result) throws JSONException {
                if (result.length() == 0) {
                    CardModel m = new CardModel();
                    m.setTitle("There is no ads with specified filters");
                    m.setDescription("Try again with other filters");
                    m.setImgURL("https://thumbs.dreamstime.com/z/ludzie-biznesu-oceny-quesiton-wzruszenie-ramionami-279604.jpg");
                    models.add(m);
                } else {
                    for (int i = 0; i < result.length(); i++) {
                        CardModel m = new CardModel();
                        JSONObject obj = result.getJSONObject(i);
                        m.setDescription(obj.getString("ad_description"));
                        m.setTitle(obj.getString("title"));
                        try {
                            m.setImgURL(obj.getString("photo_link"));
                            m.setEmail(obj.getString("trener_email"));
                            m.setPhone(obj.getString("trener_phone"));
                        } catch (JSONException e) {
                            m.setImgURL("https://www.mixmedia.pl/temp/aktualnosci/znak%20zapytania.jpg");
                            m.setEmail("random@email.com");
                            m.setPhone("123456789");
                        }
                        m.setAd_id(obj.getString("advertisement_id"));
                        m.setAddress(obj.getString("address"));
                        m.setName(obj.getString("trener_name"));
                        m.setPrice(String.valueOf(obj.getDouble("price")));
                        m.setTrener_id(String.valueOf(obj.getInt("trener_id")));
                        m.setDate(obj.getString("date") + " " + obj.getString("time"));
                        models.add(m);
                    }

                }
                myAdapter = new MyAdapter(getActivity(), models);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(myAdapter);
                dialog.dismiss();
            }
        });
        return view;
    }
}
