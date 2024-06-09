package weatherdata;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private static final String city = "Warsaw";
    private static final String api_key = "9610116624a42999c3fabc3cc157db81";
    private static final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api_key + "&units=metric";
    private final HttpClient httpClient;
    private HttpResponse<String> response;
    public WeatherClient() {
        this.httpClient = HttpClient.newHttpClient();
    }
    public HttpResponse<String> getResponse() {
        return response;
    }

    public void setResponse(HttpResponse<String> response) {
        this.response = response;
    }

    public String weatherApiRequest() throws URISyntaxException, IOException, InterruptedException{

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .GET()
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to get data from the API: " + response.statusCode());
        }
    }
}
