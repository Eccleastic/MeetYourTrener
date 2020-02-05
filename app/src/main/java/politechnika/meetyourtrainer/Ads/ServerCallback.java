package politechnika.meetyourtrainer.Ads;

import org.json.JSONArray;
import org.json.JSONException;
//https://stackoverflow.com/questions/23833977/android-wait-for-volley-response-to-return

public interface ServerCallback {
    void onSuccess(JSONArray result) throws JSONException;
}