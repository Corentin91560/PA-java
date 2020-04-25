package sample.api;

import org.json.JSONArray;
import org.json.JSONObject;

import sample.Class.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ApiCaller {
    private String apiPath = "http://localhost:3000/";

    private static ApiCaller instance;

    public static ApiCaller getInstance() {
        if (instance == null) {
            instance = new ApiCaller();
        }
        return instance;
    }

    private ApiCaller(){

    }

    public int signInUser(User user){
        HttpURLConnection con;
        String url = apiPath+"signin";
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");

            JSONObject object = new JSONObject();
            object.put("login",user.getLogin());
            object.put("password",user.getPassword());
            OutputStream os = con.getOutputStream();
            os.write(object.toString().getBytes("UTF-8"));
            os.close();

            return con.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
        return 500;
    }
}
