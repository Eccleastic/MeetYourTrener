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

public class AdInfoProvider {
    String apiData;

    public void getAdsByTrenerId(final Context c, String trener_id, final ServerCallback callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getAdvertisementsByTrenerID?code=csOSdasazaWGma/QvxV95djvs/ha8J0eHwFaAIgbxGXK7O0iGEWLjA==&trenerid=");
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

    public void getAdByAdId(final Context c, String ad_id, final ServerCallbackTwo callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getAdvertisementByID?code=sAm2FXu0NKejDUJ1CeIRSiIQPusXbMJNMu11p2WyWciajng6evhbkw==&advertisementid=");
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

    public void getAdByFilters(final Context c, String latitude, String longitude, String distance, String maxdate, String maxprice, final ServerCallback callback) {

        StringBuilder stringBuilder = new StringBuilder();
        if (distance.isEmpty())
            distance = "10";
        double distance_in_m = Double.valueOf(distance) * 1000;
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/getFilteredAdvertisements?code=D/1t9HlHucQQHaU37B6LvTT2ArUrcwtaOXXJIRdc0TtWkAxlTBcnFA==");
        stringBuilder.append("&lat=").append(latitude).append("&long=").append(longitude);
        stringBuilder.append("&maxdist=").append(distance_in_m);
        stringBuilder.append("&maxdate=").append(maxdate);
        stringBuilder.append("&maxprice=").append(maxprice);

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

    public void createNewAd(final Context c, String latitude, String longitude, String title, String trenerid, String date, String price, String description, String time, String address, final ServerCallbackTwo callback) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/addNewAdvertisement?code=U83zm2b5UVmmumaoElP8JlDeBucqtgdEOmSxasySdRHz1goKQXaonQ==");
        stringBuilder.append("&lat=").append(latitude).append("&long=").append(longitude);
        stringBuilder.append("&title=").append(title.replace(" ", "%20"));
        stringBuilder.append("&trenerid=").append(trenerid);
        stringBuilder.append("&userid=").append(trenerid);
        stringBuilder.append("&date=").append(date);
        stringBuilder.append("&time=").append(time);
        stringBuilder.append("&price=").append(price);
        stringBuilder.append("&address=").append(address.replace(" ", "%20"));
        stringBuilder.append("&description=").append(description.replace(" ", "%20"));

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

    public void editAd(final Context c, String advertisementid, String lat, String longi, String title, String trenerid, String date, String time, String price, String address, String description, final ServerCallbackTwo callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/editAdvertisement?code=jz6bIPfM/qBO9lMzqYEzBQqmLUe5wt16ThIjibPHmMuDy9yIkv6isA==");
        stringBuilder.append("&advertisementid=").append(advertisementid);
        stringBuilder.append("&lat=").append(lat);
        stringBuilder.append("&long=").append(longi);
        stringBuilder.append("&title=").append(title.replace(" ", "%20"));
        stringBuilder.append("&trenerid=").append(trenerid);
        stringBuilder.append("&date=").append(date);
        stringBuilder.append("&time=").append(time);
        stringBuilder.append("&price=").append(price);
        stringBuilder.append("&address=").append(address.replace(" ", "%20"));
        stringBuilder.append("&description=").append(description.replace(" ", "%20"));

        String url = stringBuilder.toString();
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            try {
                callback.onSuccess(response); // call callback function here
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
