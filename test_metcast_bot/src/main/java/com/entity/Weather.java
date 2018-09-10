package com.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {


    public static String  getWeather(String message, Model model) throws IOException {
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=ab38f537466714f231ab6da69faae8b4");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner((InputStream) url.getContent());

        String res="";

        while (scanner.hasNext()){
            res+=scanner.nextLine();
        }
        JSONObject jsonObject = new JSONObject(res);
        model.setName(jsonObject.getString("name"));

        JSONObject mainJson = jsonObject.getJSONObject("main");
        model.setTemp(mainJson.getDouble("temp"));
        model.setHumidity(mainJson.getDouble("humidity"));

        JSONArray jsonArray = jsonObject.getJSONArray("weather");
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject jobj = jsonArray.getJSONObject(i);
            model.setIcon((String) jobj.get("icon"));
            model.setMain((String) jobj.get("main"));
        }


        return "City -> "+ model.getName()+"\n"+
                "Temperature -> "+model.getTemp()+"\n"+
                "Humidity -> "+model.getHumidity()+"\n"+
                "Main ->"+model.getMain()+"\n"+
                "http://openweathermap.org/img/w/"+model.getIcon()+".png";

    }
}
