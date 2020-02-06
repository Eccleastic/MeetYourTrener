package politechnika.meetyourtrainer.Register;

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

public class RegisterUserProvider {

    public void registerUser(final Context c, String login, String password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://mytfunctions.azurewebsites.net/api/registerNewUser?code=M167UGpb2vMRBBZPddUtMDLY0Hh33bAlsZsgWorHEzumqjOBlu0t9Q==");
        stringBuilder.append("&name=");
        stringBuilder.append(login);
        stringBuilder.append("&password=");
        stringBuilder.append(password);

        String url = stringBuilder.toString();
        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.POST, url, null, response -> {
            //do nothing
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
