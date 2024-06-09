package weatherdata;

public class Main {
    public static void main(String[] args) {

    WeatherData weatherData= new WeatherData();
    CurrentWeatherDisplay currentWeatherDisplay1= new CurrentWeatherDisplay();

    weatherData.registerObserver(currentWeatherDisplay1);
    Runnable queries = new Runnable() {
        @Override
        public void run() {
            while(true) {
                weatherData.measurementChanged();
                currentWeatherDisplay1.display();
                System.out.println("-----------");
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    };

    //weatherData.measurementChanged();
    System.out.println(weatherData);
        System.out.println("-----------");
    //currentWeatherDisplay1.display();

     Thread thread = new Thread(queries);
     thread.start();

    }
}