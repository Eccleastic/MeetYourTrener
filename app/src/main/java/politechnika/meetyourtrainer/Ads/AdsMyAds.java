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

        View view = inflater.inflate(R.layout.fragment_myads, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewMyAds);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, "Please Wait");
        AdInfoProvider adInfo = new AdInfoProvider();
        ArrayList<CardModel> models = new ArrayList<>();
        adInfo.getDataFromApi(getActivity(), new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                if (result.length() == 0) {
                    System.out.println("RESULT LENGTH: 0");
                    CardModel m = new CardModel();
                    m.setTitle("You have no Ads");
                    m.setDesctiption("");
                    m.setImgURL("");
                    models.add(m);
                } else {
                    System.out.println("RESULT LENGTH:" + result.length());
                    CardModel m = new CardModel();
                    JSONObject obj = result;
                    m.setDesctiption(obj.getString("description") + " " + obj.getString("price"));
                    m.setTitle(obj.getString("title"));
                    m.setImgURL(obj.getString("user_photo"));
                    models.add(m);
                    System.out.println(result.toString());

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
