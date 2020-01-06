package politechnika.meetyourtrainer.Ads;

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
import org.json.JSONObject;

public class AdInfoProvider {
    String apiData;

    public void getAdsByTrenerId(final Context c,String trener_id, final ServerCallback callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://meetyourtrenerspringfunctions.azurewebsites.net/api/getAdvertisementsByTrenerID?code=VGWDvsqQYI6H902rudUdG4Jo/MzU1g3zM0DUX6DIaa/E3vtsGP82sg==&trenerid=");
        stringBuilder.append(trener_id);

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

    public void getAdByAdId(final Context c,String ad_id, final ServerCallbackTwo callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://http://meetyourtrenerspringfunctions.azurewebsites.net/api/getAdvertisementByID?code=5kQw/Ozkaa39pwcylYOgH4vSRuIIqySNah1dDJMU4hq2ROgeW0c4tQ==&advertisementid=");
        stringBuilder.append(ad_id);

        apiData = stringBuilder.toString();

        String url = stringBuilder.toString();
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
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
