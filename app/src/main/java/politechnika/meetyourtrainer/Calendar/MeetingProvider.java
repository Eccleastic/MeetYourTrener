package politechnika.meetyourtrainer.Calendar;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import politechnika.meetyourtrainer.R;

public class MeetingProvider {
    String apiData;
    String dateStart;
    String dateEnd;

    ArrayList<CardModel> models = new ArrayList<>();


    public MeetingProvider(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }


    public void getDataFromApi(Context c) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://meetyourtrenerspringfunctions.azurewebsites.net").append("/api/getMeetingsFromDateToDate?code=rSElQLtf");
        stringBuilder.append("/xYOOhG/iPF0jVKXjze9aWPb4fwq8kYq3NMzmBtS3i20Yg==&startdate=");
        stringBuilder.append(dateStart).append("&enddate=").append(dateEnd);

        apiData = stringBuilder.toString();
        System.out.println(apiData);

        String url = stringBuilder.toString();
        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                System.out.println(response.length());
                for (int i = 0; i < response.length(); i++) {
                    CardModel m = new CardModel();
                    JSONObject obj = response.getJSONObject(i);
                    m.setDesctiption(obj.getString("note") + ",\n" + obj.getString("meeting_address") + ",\n" + obj.getString("meeting_date"));
                    m.setTitle("trener_ID: " + obj.getInt("trener_ID"));
                    m.setImg(R.drawable.face);
                    models.add(m);
                    System.out.println(response.getJSONObject(i).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            try {
                if (error instanceof TimeoutError) {
                    System.out.println("TimeoutError");
                } else if (error instanceof NoConnectionError) {
                    System.out.println("NoConnectionError");
                } else if (error instanceof AuthFailureError) {
                    System.out.println("AuthFailureError");
                } else if (error instanceof ServerError) {
                    System.out.println("ServerError");
                } else if (error instanceof NetworkError) {
                    System.out.println("NetworkError");
                } else if (error instanceof ParseError) {
                    System.out.println("ParseError");
                }
            } catch (Exception e) {
            }
        }
        );
        RequestQueue q = Volley.newRequestQueue(c);
        q.add(jor);
    }
}
