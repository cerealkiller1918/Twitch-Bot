package com.justin.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.justin.stackTrace.StackTrace;
import com.justin.webInterface.WebInterface;

/**
 * Created by Justin Frasier on 07/28/16.
 */
public class Weather {

    private static String key = "1299ede563bb4be7";


    public static String[] getForecast(String city, String state){
        try {
            StringBuilder builder = new StringBuilder();
            if(city.contains(" ")){
                builder.append(city);
                builder.replace(builder.indexOf(" "),builder.indexOf(" ")+1,"_");
                city = builder.toString();
            }
            String url = "http://api.wunderground.com/api/" + key + "/conditions/q/" + state + "/" + city + ".json";
            StringBuilder
                    location = new StringBuilder(),
                    weather = new StringBuilder(),
                    temperature_string = new StringBuilder(),
                    wind_string = new StringBuilder(),
                    observation_time = new StringBuilder(),
                    visibility_mi = new StringBuilder(),
                    feelsLike_string = new StringBuilder(),
                    precipitation_1hr_string = new StringBuilder(),
                    precipitation_today_string = new StringBuilder(),
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
            temperature_string.append(current_observation.get("temperature_string").toString());
            temperature_string = trim(temperature_string);
            wind_string.append(current_observation.get("wind_string").toString());
            wind_string = trim(wind_string);
            observation_time.append(current_observation.get("observation_time").toString());
            observation_time = trim(observation_time);
            visibility_mi.append(current_observation.get("visibility_mi").toString());
            visibility_mi = trim(visibility_mi);
            feelsLike_string.append(current_observation.get("feelslike_string").toString());
            feelsLike_string = trim(feelsLike_string);
            precipitation_1hr_string.append(current_observation.get("precip_1hr_string").toString());
            precipitation_1hr_string = trim(precipitation_1hr_string);
            precipitation_today_string.append(current_observation.get("precip_today_string").toString());
            precipitation_today_string = trim(precipitation_today_string);
            forecast_url.append(current_observation.get("forecast_url").toString());
            forecast_url = trim(forecast_url);

            String[] output ={"Location: "+location.toString(),
                    observation_time.toString(),
                    "Weather: " + weather.toString(),
                    "Temperature: " + temperature_string.toString(),
                    "Feels Like: " + feelsLike_string.toString(),
                    "Wind Speed: " + wind_string.toString(),
                    "Visibility: " + visibility_mi.toString(),
                    "Precipitation in 1 hour: " + precipitation_1hr_string.toString(),
                    "Precipitation for the day: " + precipitation_today_string.toString(),
                     forecast_url.toString()};

            return output;
        }catch(Exception e){
            StackTrace.message(e);
            String[] output = {"Error recheck City and State spelling"};
            return output;
        }
    }

    private static StringBuilder trim (StringBuilder builder){
        try {
            for (int i = 0; i < 2; i++) {
                builder.deleteCharAt(builder.indexOf("\""));
            }
            return builder;
        }catch (Exception e){
            StackTrace.message(e);
            return new StringBuilder("null");
        }
    }

}
