package politechnika.meetyourtrainer.Ads;

import android.app.ProgressDialog;
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

import politechnika.meetyourtrainer.R;

public class AdsMyAds extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    String trener_id;

    public static AdsMyAds newInstance() {
        return new AdsMyAds();
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
        this.trener_id = "2";
        AdInfoProvider adInfo = new AdInfoProvider();
        ArrayList<CardModel> models = new ArrayList<>();
        adInfo.getAdsByTrenerId(getActivity(),trener_id, new ServerCallback() {
            @Override
            public void onSuccess(JSONArray result) throws JSONException {
                if (result.length() == 0) {
                    System.out.println("RESULT LENGTH: 0");
                    CardModel m = new CardModel();
                    m.setTitle("You have no Ads");
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


        return view;
    }
}
