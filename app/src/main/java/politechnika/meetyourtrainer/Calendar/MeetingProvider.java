package politechnika.meetyourtrainer.Calendar;

import android.app.ProgressDialog;
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

import politechnika.meetyourtrainer.Calendar.ServerCallback;
import politechnika.meetyourtrainer.R;

public class MeetingProvider {
    String apiData;
    String dateStart;
    String dateEnd;

    public MeetingProvider(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }


    public void getDataFromApi(final Context c, final ServerCallback callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getMeetingsFromDateToDate?code=u9gHu3PV3mRA4xtUcVWy6dCfNaN/AcsLow8WqTUerguIoPnEaJszWQ==&startdate=");
        stringBuilder.append(dateStart).append("&enddate=").append(dateEnd);

        apiData = stringBuilder.toString();

        String url = stringBuilder.toString();
        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                callback.onSuccess(response); // call call back function here
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
