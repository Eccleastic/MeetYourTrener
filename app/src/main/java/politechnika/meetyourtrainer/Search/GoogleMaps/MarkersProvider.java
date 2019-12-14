package politechnika.meetyourtrainer.Search.GoogleMaps;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarkersProvider {

    double userLatitude, userLongitude;
    double distance;
    List<MarkerOptions> markers = new ArrayList<>();

    MarkersProvider(double latitude, double longitude, double distance) {
        this.userLatitude = latitude;
        this.userLongitude = longitude;
        this.distance = distance;
    }

    void calculateLatLong() {
    }

    void getMarkersFromApi() {
    }

    void getMarkersByDistance() {
    }

    void getMarkersByKeyWord() {
    }

    void getMarkersByDistanceAndKeyWord() {
    }

    public void getMarkerById(int id, final Context c, final ServerCallback callback){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://meetyourtrenerspringfunctions.azurewebsites.net/api")
                .append("/getMeeting?code=YsZKBGmTdpa8Vsp4/Slc6QhqUgnTkUhQw2/R6CM0yauwPkVTxAzRNA==&meetingid=")
                .append(id);
        String url = stringBuilder.toString();

        JsonObjectRequest marker_data = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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
        q.add(marker_data);

    }


    void createMarkers() {
        LatLng budynek_CTI = new LatLng(51.746956, 19.455958);
        LatLng budynek_centrum_sportu = new LatLng(51.746256, 19.451444);
        LatLng budynek_sukcesja = new LatLng(51.749201, 19.448128);
        LatLng budynek_WEEIA = new LatLng(51.752612, 19.453118);
        LatLng ogloszenie1 = new LatLng(51.751980, 19.449657);
        markers.add(new MarkerOptions().position(budynek_CTI).title("Centrum Technologii Informatycznych"));
        markers.add(new MarkerOptions().position(budynek_centrum_sportu).title("Centrum Sportu"));
        markers.add(new MarkerOptions().position(budynek_sukcesja).title("Galeria Sukcesja"));
        markers.add(new MarkerOptions().position(budynek_WEEIA).title("Wydzial EEIA"));
        String ogloszenie1_text = "Zapraszam na lekcje wszystkie panie i panów.\n40zł/h";
        markers.add(new MarkerOptions().position(ogloszenie1).title("YOGA - Adam Pośpiech").snippet(ogloszenie1_text));

        markers.add(new MarkerOptions().position(new LatLng(51.732058, 19.453359)).title("Gimnastyka").snippet("Ola Kozakiewicz 50zl/h"));
        markers.add(new MarkerOptions().position(new LatLng(51.731147, 19.451916)).title("Crossfit").snippet("Adam Lamaga 45zl/h"));
        markers.add(new MarkerOptions().position(new LatLng(51.730482, 19.453311)).title("Dwubój olimpijski").snippet("Karol Olech 150zl/h"));
        markers.add(new MarkerOptions().position(new LatLng(51.731399, 19.452893)).title("Tróbój").snippet("Jerzy Dudek 130zl/h"));
    }

    List<MarkerOptions> getMarkers() {
        return this.markers;
    }

}
