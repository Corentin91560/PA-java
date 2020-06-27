package sample.api;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.Class.Feedback;
import sample.Class.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    public String validatebug(Feedback feedback){
        try{
            String jsonInputString = "{\"idfe\": "+feedback.getIdfe()+"}";
            System.out.println(jsonInputString);
            URL url = new URL(apiPath+"feedbackvalide");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes("UTF-8"));
            os.close();
            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode()==200 ){
                return "ok";
            }else {
                return "server "+conn.getResponseCode();
            }


        } catch (MalformedURLException e) {
            System.out.println(e);
            return "server error2";
        } catch (IOException e) {
            System.out.println(e);
            return "server error3";
        }
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
            user.setError("Internal error");
            return user;
        } catch (IOException e) {
            user.setError("Internal server error");
            return user;
        }
        return null;
    }
    public List<Feedback> Returnbug(){
        List<Feedback> bugList = new ArrayList<>();
        try {

            URL url = new URL(apiPath+"feedback/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

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
                JSONArray jsonArray = new JSONArray(response.toString());

                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i=0;i<len;i++){
                        bugList.add(new Feedback(jsonArray.getJSONObject(i).getInt("idfe"),
                                jsonArray.getJSONObject(i).getString("title"),
                                jsonArray.getJSONObject(i).getString("content"),
                                jsonArray.getJSONObject(i).getString("date"),
                                jsonArray.getJSONObject(i).getString("status"),
                                jsonArray.getJSONObject(i).getString("plateform")));
                    }
                }
                conn.disconnect();

                return bugList;
            }else{
                System.out.println("buglist error");
                return bugList;
            }


        } catch (MalformedURLException e) {
            System.out.println("malformated"+e);
            return bugList;
        } catch (IOException e) {
            System.out.println("io exception"+e);
            return bugList;
        }
    }
    public List<Feedback> Returnimprove(){
        List<Feedback> improveList = new ArrayList<>();
        try {

            URL url = new URL(apiPath+"feedback/2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

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
                JSONArray jsonArray = new JSONArray(response.toString());

                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i=0;i<len;i++){
                        improveList.add(new Feedback(jsonArray.getJSONObject(i).getInt("idfe"),
                                jsonArray.getJSONObject(i).getString("title"),
                                jsonArray.getJSONObject(i).getString("content"),
                                jsonArray.getJSONObject(i).getString("date"),
                                jsonArray.getJSONObject(i).getString("status"),
                                jsonArray.getJSONObject(i).getString("plateform")));
                    }
                }
                conn.disconnect();

                return improveList;
            }else{
                System.out.println("buglist error");
                return improveList;
            }


        } catch (MalformedURLException e) {
            System.out.println("malformated"+e);
            return improveList;
        } catch (IOException e) {
            System.out.println("io exception"+e);
            return improveList;
        }
    }
    public String BugToTrello(User admin,Feedback feedback){

        String key = "",token = "";

        if (admin.getLogin().equals("admin_01")){
             key = "2c976bc121a7f659228a9b247acae3f3";
             token = "f28c03481c79f1558dc0fbc5d5b5268edb532a54ee97f9d179d79c35cf94a1d4";
        }
        if (admin.getLogin()=="admin_02"){
             key = "4d2084403ff7b6e3eaaddba24c4524d3";
             token = "428e4779cb7c9480f9076faafb5652ebb7e34130cace3074bee458a2e62a606c";
        }
        if (admin.getLogin()=="admin_03"){
             key = "";
             token = "";
        }

        try{
            String jsonInputString = "{\"appli\": \""+feedback.getPlateform()+"\", \"idfe\": \""+feedback.getIdfe()+"\", \"key\": \""+key+"\",\"token\": \""+token+"\", \"name\": \""+feedback.getTitle()+"\", \"desc\": \""+feedback.getContent()+"\"}";
            System.out.println(jsonInputString);
            URL url = new URL(apiPath+"trello/feedback");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes("UTF-8"));
            os.close();
            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode()==200 ){
                return "ok";
            }else {
                return "server error1";
            }


        } catch (MalformedURLException e) {
            System.out.println(e);
            return "server error2";
        } catch (IOException e) {
            System.out.println(e);
            return "server error3";
        }
        }
}
