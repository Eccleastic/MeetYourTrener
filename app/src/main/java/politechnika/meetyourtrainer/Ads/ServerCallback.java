package politechnika.meetyourtrainer.Ads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//https://stackoverflow.com/questions/23833977/android-wait-for-volley-response-to-return

public interface ServerCallback{
    void onSuccess(JSONObject result) throws JSONException;
}