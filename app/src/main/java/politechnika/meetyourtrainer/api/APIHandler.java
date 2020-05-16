package politechnika.meetyourtrainer.api;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


/**
 *  Api handler do połączenia aplikacji z Azure Functions
 */

public class APIHandler {

    private static final String USER_CREDENTIAL_STRING = "https://mytfunctions.azurewebsites.net/api/checkLoginCredentials?code=kziVgpuTgUhaCOdPvAtwotJKXp7nbkpagLh/x7JSmzQLRS6cnhvIkw==";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            System.out.println(jsonText);
            System.out.println(json.toString());
            return json;
        } finally {
            is.close();
        }
    }

    public static boolean confirUserNameAndUserPasswordWithDatabase(String userName, String userPassword) {
        try {
            String stringUrl = USER_CREDENTIAL_STRING + "&name=" + userName + "&password=" + userPassword + "";
            JSONObject jsonObject = readJsonFromUrl(stringUrl);
            System.out.println(jsonObject.getInt("user_id"));
            if (jsonObject.get("loginSucessful").equals(true))
                return true;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerNewUser(String userName, String userPassword){
        //String stringUrl =
    }

    public static void userAuthentication(String userName, String userPassword, final Context c, final ServerCallback callback){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(USER_CREDENTIAL_STRING);
        stringBuilder.append("&name=");
        stringBuilder.append(userName);
        stringBuilder.append("&password=");
        stringBuilder.append(userPassword);
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
