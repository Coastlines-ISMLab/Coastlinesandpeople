package com.example.wear;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherUpdate {
    static String CLIENT_ID = "5ubJAGCLQtU0rr3qnbeuO ";
    static String CLIENT_SECRET = "pSHdVCUWtD1sagvskXghgmQVEqwnOScxgCYvx1cA";
    static String BASE_URL = "http://api.aerisapi.com/";

    public static List<String> getCurrentTemp() {
        List<String> observations = new ArrayList<>();
        System.out.println("******************weather****************************");
        try {
            String apiPath = BASE_URL + "observations/tampa,fl?&format=json&filter=allstations&limit=1&client_id="+CLIENT_ID+ "&client_secret="+CLIENT_SECRET;
            String currentWeather = "";
            URL urlObj = new URL(apiPath);
            HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
            //int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + apiPath);
            //System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new String(response));

            System.out.println("Current temp in F :");
            System.out.println(((JSONObject)((JSONObject) jsonObject.get("response")).get("ob")).get("tempF"));
            JSONObject responseObject = (JSONObject) jsonObject.get("response");
            String currentTemp = ((JSONObject)((JSONObject) jsonObject.get("response")).get("ob")).get("tempF").toString();
            System.out.println(responseObject.get("ob"));
            observations.add(currentTemp);

        }
        catch(Exception e) {
            System.out.println("Exception :"+ e);
        }
        System.out.println("observation is "+observations);
        System.out.println("******************weather****************************");
        return observations;
    }

}
