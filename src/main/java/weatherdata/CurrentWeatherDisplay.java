package weatherdata;

public class CurrentWeatherDisplay implements  Display, Observer, Runnable {
    private double temperature;
    private double humidity;
    private double pressure;

    @Override
    public void display() {
        System.out.println("CURRENT WEATHER DATA");
        System.out.println("Current temp: " + temperature);
        System.out.println("Current hum: " + humidity);
        System.out.println("Current press: " + pressure);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @Override
    public void run() {
        while(true) {
            try {
                display();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
