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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ApiCaller {

    private String apiUrl = "https://benevent-esgi.herokuapp.com/";

    private static ApiCaller instance;

    public static ApiCaller getInstance() {
        if (instance == null) {
            instance = new ApiCaller();
        }
        return instance;
    }

    private ApiCaller(){

    }

    public Admin signInAdmin(Admin admin){

        try {
            String jsonInputString = "{\"login\": \""+admin.getLogin()+"\", \"password\": \""+admin.getPassword()+"\"}";
            URL url = new URL(apiUrl+"signin/admin");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes(StandardCharsets.UTF_8));
            os.close();
            if (conn.getResponseCode()==200 ){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject myResponse = new JSONObject(response.toString());
                admin.setEmail(myResponse.getString("email"));
                admin.setIda(myResponse.getInt("idadmin"));

                conn.disconnect();

                return admin;
            }else if(conn.getResponseCode()==401){
                admin.setError("Le login ou le mot de passe est incorrect");
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

            URL url = new URL(apiUrl+"feedback/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode()==200){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());

                int length = jsonArray.length();
                for (int i=0;i<length;i++){
                    if(jsonArray.getJSONObject(i).get("status")==JSONObject.NULL){
                        bugList.add(new Feedback(jsonArray.getJSONObject(i).getInt("idfeedback"),
                                jsonArray.getJSONObject(i).getString("title"),
                                jsonArray.getJSONObject(i).getString("content"),
                                jsonArray.getJSONObject(i).getString("date"),
                                "",
                                jsonArray.getJSONObject(i).getString("plateform")));
                    }else{
                        bugList.add(new Feedback(jsonArray.getJSONObject(i).getInt("idfeedback"),
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
                //retourner en fenetre conn.getResponseCode()
                return bugList;
            }

        } catch (IOException e) {
            //retourner en fenetre e
            return bugList;
        }
    }

    public List<Feedback> Returnimprove(){
        List<Feedback> improveList = new ArrayList<>();
        try {

            URL url = new URL(apiUrl+"feedback/2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode()==200 ){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONArray jsonArray = new JSONArray(response.toString());

                int length = jsonArray.length();
                for (int i=0;i<length;i++){
                    if(jsonArray.getJSONObject(i).get("iduser")!=JSONObject.NULL){
                        improveList.add(new Feedback(
                                jsonArray.getJSONObject(i).getString("content"),
                                jsonArray.getJSONObject(i).getString("date"),
                                jsonArray.getJSONObject(i).getInt("note"),
                                jsonArray.getJSONObject(i).getString("plateform"),
                                jsonArray.getJSONObject(i).getInt("iduser")));
                    }else{
                        improveList.add(new Feedback(
                                jsonArray.getJSONObject(i).getString("content"),
                                jsonArray.getJSONObject(i).getString("date"),
                                jsonArray.getJSONObject(i).getInt("note"),
                                jsonArray.getJSONObject(i).getInt("idassociation"),
                                jsonArray.getJSONObject(i).getString("plateform")
                                ));
                    }
                }
                conn.disconnect();

                return improveList;
            }else{
                return improveList;
            }


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
        if (admin.getLogin().equals("admin_02")){
             key = "4d2084403ff7b6e3eaaddba24c4524d3";
             token = "428e4779cb7c9480f9076faafb5652ebb7e34130cace3074bee458a2e62a606c";
        }
        if (admin.getLogin().equals("admin_03")){
             key = "";
             token = "";
        }

        try{
            String jsonInputString = "{\"appli\": \""+feedback.getPlateform()+"\", \"idfeedback\": \""+feedback.getIdfe()+"\", \"key\": \""+key+"\",\"token\": \""+token+"\", \"name\": \""+feedback.getTitle()+"\", \"desc\": \""+content+"\", \"status\": \"pending\"}";
            URL url = new URL(apiUrl+"trello/feedback");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes(StandardCharsets.UTF_8));
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

    public String validatebug(Feedback feedback){
        try{
            String jsonInputString = "{\"status\": \"validate\"}";
            URL url = new URL(apiUrl+"feedback/"+feedback.getIdfe());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("PUT");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInputString.getBytes(StandardCharsets.UTF_8));
            os.close();
            if (conn.getResponseCode()==200 ){
                return "Votre Bug a bien été validé";
            }else {
                return "serveur indisponible code de réponse : "+conn.getResponseCode();
            }

        } catch (IOException e) {
            return "erreur interne";
        }
    }

    public User getUserInfo(int iduser){
        User user = new User(iduser);

        try{
            URL url = new URL(apiUrl+"user/detail/"+iduser);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode()==200 ){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
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
                user.setIdu(myResponse.getInt("iduser"));


                conn.disconnect();
                return user;
            }else if(conn.getResponseCode()==401){
                return user;
            }

        } catch (IOException e) {
            return user;
        }

        return user;
    }

    public Association getAssoInfo(int idasso){

        Association association = new Association(idasso);

        try{
            URL url = new URL(apiUrl+"associationdetail/"+idasso);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode()==200 ){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

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
                association.setIdas(myResponse.getInt("idassociation"));


                conn.disconnect();
                return association;
            }else if(conn.getResponseCode()==401){
                return association;
            }


        } catch (IOException e) {
            return association;
        }

        return association;
    }

}
