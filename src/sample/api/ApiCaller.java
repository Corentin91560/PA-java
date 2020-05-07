package sample.api;

import org.json.JSONObject;
import org.json.JSONTokener;
import sample.Class.User;

import java.io.*;
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

    public User signInUser(User user){

        try {
        String jsonInputString = "{\"login\": \""+user.getLogin()+"\", \"password\": \""+user.getPassword()+"\"}";
        URL url = new URL(apiPath+"signin/admin");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes("UTF-8"));
            os.close();
            if (conn.getResponseCode()==200 ){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print response
                System.out.println(response.toString());
                JSONObject myResponse = new JSONObject(response.toString());
                user.setEmail(myResponse.getString("email"));
                user.setIda(myResponse.getInt("ida"));

                conn.disconnect();

                return user;
            }else if(conn.getResponseCode()==401){
                user.setError("Login or password is incorrect");
                return user;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
