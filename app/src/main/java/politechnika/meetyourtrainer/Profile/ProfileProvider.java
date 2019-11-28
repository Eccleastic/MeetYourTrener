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

    String userId;
    String username;
    String name;
    String lastname;
    String description;
    String email;
    double rating;
    String phoneNumber;
    int sex;
    int age;

    public ProfileProvider(String username) {
        this.username = username;
    }

    public void getDataFromApi(Context c) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://meetyourtrenerspringfunctions.azurewebsites.net/")
                .append("api/getUserData?code=WfFchO6iJS2vWHuc7rrY95auXXaVBZJyY2i58r6oAM0QVkX9x3RuLw==&username=")
                .append(this.username);
        String url = stringBuilder.toString();
        JsonObjectRequest profile_data = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    setUserId(response.getString("user_ID"));
                    setName(response.getString("user_name"));
                    setSex(Integer.valueOf(response.getString("sex")));
                    setEmail(response.getString("contact_email"));
                    setPhoneNumber(response.getString("contact_phone"));
                    setName(response.getString("first_name"));
                    setLastname(response.getString("last_name"));
                    setRating(Double.valueOf(response.getString("user_rating")));
                    setDescription(response.getString("description"));

                    System.out.println(response.getString("description"));
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
