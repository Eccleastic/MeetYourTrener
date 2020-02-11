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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import politechnika.meetyourtrainer.Ads.ServerCallbackTwo;

public class MeetingProvider {
    String apiData;


    public void getMeetingsFromDateToDate(final Context c, String dateStart, String dateEnd, String userid, final ServerCallback callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getMeetingsFromDateToDate?code=u9gHu3PV3mRA4xtUcVWy6dCfNaN/AcsLow8WqTUerguIoPnEaJszWQ==");
        stringBuilder.append("&startdate=").append(dateStart).append("&enddate=").append(dateEnd).append("&userid=").append(userid);

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

    public void createMeeting(final Context c,String advertisementid, String userid, String trenerid, String meetingdate, String meetingaddress, String latitude, String longitude,String price, String note, final ServerCallbackTwo callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/postNewMeeting?code=yuepIuZVXcsyyhiSaIZEQBBN0LVN2M7jRA4Ul0A/9XKuP05CcpF6Sw==");
        stringBuilder
                .append("&advertisementid=").append(advertisementid.replace(" ", "%20"))
                .append("&userid=").append(userid.replace(" ", "%20"))
                .append("&trenerid=").append(trenerid.replace(" ", "%20"))
                .append("&meetingdate=").append(meetingdate.replace(" ", "%20"))
                .append("&meetingaddress=").append(meetingaddress.replace(" ", "%20"))
                .append("&latitude=").append(latitude)
                .append("&longitude=").append(longitude)
                .append("&price=").append(price)
                .append("&note=").append(note.replace(" ", "%20"));

        apiData = stringBuilder.toString();

        String url = stringBuilder.toString();
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
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
