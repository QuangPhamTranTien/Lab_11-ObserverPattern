package query;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.*;
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=wroclaw&appid=9610116624a42999c3fabc3cc157db81";

       // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .GET()
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.toString());
        System.out.println("---");
        System.out.println(response.body());
        System.out.println("----");

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        String country = jsonObject.getAsJsonObject("sys").get("country").getAsString();
        String name = jsonObject.get("name").getAsString();
        double temp = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        double pressure = jsonObject.getAsJsonObject("main").get("pressure").getAsDouble();
        double humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsDouble();
        System.out.println("City: " + name + ", country: " + country);
        System.out.printf("Temperature: %.2f\nPressure %.2f\nHumidity: %.2f",temp, pressure, humidity);
    }
}