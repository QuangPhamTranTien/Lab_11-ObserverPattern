package weatherdata;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class WeatherData implements Subject {

    private  double temperature;
    private  double humidity;
    private  double pressure;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        if (!observers.contains(o))observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {

        if(observers.contains(o)) observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers)
            o.update(temperature,humidity,pressure);
    }

    public void measurementChanged(){
        try {
            Gson gson = new Gson();
            WeatherClient weatherClient = new WeatherClient();
            String response = weatherClient.weatherApiRequest();

            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

            temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            pressure = jsonObject.getAsJsonObject("main").get("pressure").getAsDouble();
            humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsDouble();;
            notifyObserver();
        }
        catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", observers=" + observers +
                '}';
    }



}
