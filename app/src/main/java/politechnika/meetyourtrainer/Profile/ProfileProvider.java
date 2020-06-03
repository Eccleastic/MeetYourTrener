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

    public void getProfileById(final Context c, String user_id, final ServerCallback callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getUserTrenerDataByID?code=0nziF0HYsJUhLCtdX/T7VVZiBWm9lgxTZlyoKjEddIFBNWyc4IE48Q==&trenerid=").append(user_id);
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

    public void editUserProfileInfo(final Context c, String userid, String userpassword, String sex, String age,
                                    String email, String phone, String firstname, String lastname, String description, final ServerCallback callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/editUser?code=UK3AKT1qA9RmQS9/pI7I/r5I5ohI11sGNG6u5vEuFnVEXG7y696SbA==");
        stringBuilder.append("&userid=").append(userid);
        stringBuilder.append("&userpassword=").append(userpassword);
        stringBuilder.append("&sex=").append(sex);
        stringBuilder.append("&age=").append(age);
        stringBuilder.append("&email=").append(email.replace(" ", "%20"));
        stringBuilder.append("&phone=").append(phone.replace(" ", "%20"));
        stringBuilder.append("&firstname=").append(firstname.replace(" ", "%20"));
        stringBuilder.append("&lastname=").append(lastname.replace(" ", "%20"));
        stringBuilder.append("&description=").append(description.replace(" ", "%20"));

        String url = stringBuilder.toString();
        JsonObjectRequest profile_data = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
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
