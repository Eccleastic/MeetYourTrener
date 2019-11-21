package politechnika.meetyourtrainer.api;


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

    private static final String USER_CREDENTIAL_STRING = "https://meetyourtrenerspringfunctions.azurewebsites.net/api/checkLoginCredentials?code=1ANf2BApOKQAiivmwWc3D3UwHUzvuN9WxRYgfw/7UCgPr3Yk/CRCkQ==";

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
            if (jsonObject.get("loginSucessful").equals(true))
                return true;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerNewUser(String userName, String userPassword){
        String stringUrl =
    }

}
