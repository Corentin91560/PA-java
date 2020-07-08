package sample.api;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.Class.Admin;
import sample.Class.Association;
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
            URL url = new URL(apiPath+"feedback/"+feedback.getIdfe());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("PUT");

            if (conn.getResponseCode()==200 ){
                return "ok";
            }else {
                return "server "+conn.getResponseCode();
            }

        } catch (MalformedURLException e) {
            return "server error2";
        } catch (IOException e) {
            return "server error3";
        }
    }
    public User getUserInfo(int iduser){
        User user = new User(iduser);

        try{
            URL url = new URL(apiPath+"user/"+iduser);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

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
                JSONObject myResponse = new JSONObject(response.toString());
                if (myResponse.get("phone")!=JSONObject.NULL){
                    user.setPhone(myResponse.getString("phone"));
                }else{
                    user.setPhone("Non Renseigné");
                }
                if (myResponse.get("profilpicture")!=JSONObject.NULL){
                    user.setProfilpicture(myResponse.getString("profilpicture"));
                }else{
                    user.setProfilpicture("file:../ressource/images.jpg");
                }
                user.setEmail(myResponse.getString("email"));
                user.setFirstname(myResponse.getString("firstname"));
                user.setName(myResponse.getString("name"));
                user.setIdu(myResponse.getInt("idu"));


                conn.disconnect();
                return user;
            }else if(conn.getResponseCode()==401){
                return user;
            }


        }catch (MalformedURLException e) {
            return user;
        } catch (IOException e) {
            return user;
        }

        return user;
    }

    public Association getAssoInfo(int idasso){
        Association association = new Association(idasso);

        try{
            URL url = new URL(apiPath+"association/"+idasso);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

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
                JSONObject myResponse = new JSONObject(response.toString());
                if (myResponse.get("phone")!=JSONObject.NULL){
                    association.setPhone(myResponse.getString("phone"));
                }else{
                    association.setPhone("Non Renseigné");
                }
                if (myResponse.get("logo")!=JSONObject.NULL){
                    association.setLogo(myResponse.getString("logo"));
                }else{
                    association.setLogo("file:../ressource/images.jpg");
                }
                association.setEmail(myResponse.getString("email"));
                association.setName(myResponse.getString("name"));
                association.setIdas(myResponse.getInt("idas"));


                conn.disconnect();
                return association;
            }else if(conn.getResponseCode()==401){
                return association;
            }


        }catch (MalformedURLException e) {
            return association;
        } catch (IOException e) {
            return association;
        }

        return association;
    }

    public Admin signInAdmin(Admin admin){

        try {
            String jsonInputString = "{\"login\": \""+admin.getLogin()+"\", \"password\": \""+admin.getPassword()+"\"}";
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
                JSONObject myResponse = new JSONObject(response.toString());
                admin.setEmail(myResponse.getString("email"));
                admin.setIda(myResponse.getInt("ida"));

                conn.disconnect();

                return admin;
            }else if(conn.getResponseCode()==401){
                admin.setError("Login or password is incorrect");
                return admin;
            }


        } catch (MalformedURLException e) {
            admin.setError("Internal error");
            return admin;
        } catch (IOException e) {
            admin.setError("Internal server error");
            return admin;
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
                return bugList;
            }


        } catch (MalformedURLException e) {
            return bugList;
        } catch (IOException e) {
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
                        if(jsonArray.getJSONObject(i).get("idu")!=JSONObject.NULL){
                            improveList.add(new Feedback(
                                    jsonArray.getJSONObject(i).getString("content"),
                                    jsonArray.getJSONObject(i).getString("date"),
                                    jsonArray.getJSONObject(i).getInt("note"),
                                    jsonArray.getJSONObject(i).getString("plateform"),
                                    jsonArray.getJSONObject(i).getInt("idu")));
                        }else{
                            improveList.add(new Feedback(
                                    jsonArray.getJSONObject(i).getString("content"),
                                    jsonArray.getJSONObject(i).getString("date"),
                                    jsonArray.getJSONObject(i).getInt("note"),
                                    jsonArray.getJSONObject(i).getInt("idas"),
                                    jsonArray.getJSONObject(i).getString("plateform")
                                    ));
                        }


                    }
                }
                conn.disconnect();

                return improveList;
            }else{
                return improveList;
            }


        } catch (MalformedURLException e) {
            return improveList;
        } catch (IOException e) {
            return improveList;
        }
    }
    public String BugToTrello(Admin admin,Feedback feedback,String content){

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
            String jsonInputString = "{\"appli\": \""+feedback.getPlateform()+"\", \"idfe\": \""+feedback.getIdfe()+"\", \"key\": \""+key+"\",\"token\": \""+token+"\", \"name\": \""+feedback.getTitle()+"\", \"desc\": \""+content+"\"}";
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
            if (conn.getResponseCode()==200 ){
                return "ok";
            }else {
                return "server error1";
            }


        } catch (MalformedURLException e) {
            return "server error2";
        } catch (IOException e) {
            return "server error3";
        }
        }
}
