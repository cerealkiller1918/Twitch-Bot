package com.justin.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.justin.stackTrace.StackTrace;
import com.justin.webInterface.WebInterface;

/**
 * Created by Justin Frasier on 07/28/16.
 */
public class Weather {

    private String key = "1299ede563bb4be7";
    private String city;

    public String getForcast(String city, String state){
        try {
            StringBuilder builder = new StringBuilder();
            if(city.contains(" ")){
                builder.append(city);
                builder.replace(builder.indexOf(" "),builder.indexOf(" ")+1,"_");
                this.city = builder.toString();

            }
            String url = "http://api.wunderground.com/api/" + key + "/conditions/q/" + state + "/" + this.city + ".json";
            String output;
            StringBuilder
                    location = new StringBuilder(),
                    weather = new StringBuilder(),
                    temperatur_string = new StringBuilder(),
                    wind_string = new StringBuilder(),
                    observation_time = new StringBuilder(),
                    visibility_mi = new StringBuilder(),
                    feelslike_string = new StringBuilder(),
                    precip_1hr_string = new StringBuilder(),
                    precip_today_string = new StringBuilder(),
                    forecast_url = new StringBuilder();
            WebInterface web = new WebInterface();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(web.getHTTP(url));
            JsonObject current_observation = (JsonObject) jsonObject.get("current_observation");
            JsonObject display_location = (JsonObject) current_observation.get("display_location");
            location.append(display_location.get("full").toString());
            location = trim(location);
            weather.append(current_observation.get("weather").toString());
            weather = trim(weather);
            temperatur_string.append(current_observation.get("temperature_string").toString());
            temperatur_string = trim(temperatur_string);
            wind_string.append(current_observation.get("wind_string").toString());
            wind_string = trim(wind_string);
            observation_time.append(current_observation.get("observation_time").toString());
            observation_time = trim(observation_time);
            visibility_mi.append(current_observation.get("visibility_mi").toString());
            visibility_mi = trim(visibility_mi);
            feelslike_string.append(current_observation.get("feelslike_string").toString());
            feelslike_string = trim(feelslike_string);
            precip_1hr_string.append(current_observation.get("precip_1hr_string").toString());
            precip_1hr_string = trim(precip_1hr_string);
            precip_today_string.append(current_observation.get("precip_today_string").toString());
            precip_today_string = trim(precip_today_string);
            // String heat_index_string = current_observation.get("heat_index_string").toString();
            forecast_url.append(current_observation.get("forecast_url").toString());
            forecast_url = trim(forecast_url);

            output = location.toString() + '\n' + weather.toString() + " " + temperatur_string.toString() + " " + feelslike_string.toString() + '\n' + wind_string.toString() + '\n' + visibility_mi.toString()
                    + '\n' + precip_1hr_string.toString() + '\n' + precip_today_string.toString() + '\n' + forecast_url.toString();


            return output;
        }catch(Exception e){
            StackTrace.message(e);
            return "Error recheck City and State spelling";
        }
    }

    private StringBuilder trim (StringBuilder builder){
        for(int i = 0; i<2; i++) {
            builder.deleteCharAt(builder.indexOf("\""));
        }
        return builder;
    }

}
