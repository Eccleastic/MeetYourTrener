package politechnika.meetyourtrainer.Profile;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileProvider {

    public void getProfileById(final Context c, String user_id,  final ServerCallback callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://meetyourtrenerspringfunctions.azurewebsites.net/api/getUserTrenerDataByID?code=Eb48V9JOEenJBLbNRAFJj6s3YqXCj0hI6QF5EQ4YDPDafUflTjuA5g==&trenerid=").append(user_id);
        String url = stringBuilder.toString();
        JsonObjectRequest profile_data = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccess(response); // call call back function here
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        }
        );
        RequestQueue q = Volley.newRequestQueue(c);
        q.add(profile_data);

    }
}
