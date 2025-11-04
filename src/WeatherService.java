import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherService implements Subject {
    private static final String WEATHER_URL = "http://13.238.167.130/weather";
    private final HttpClient client;
    private final List<Observer> observers;

    public WeatherService() {
        this.client = HttpClient.newHttpClient();
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(WeatherData data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    public void start() {
        Thread weatherThread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(WEATHER_URL))
                        .build();

                HttpResponse<java.util.stream.Stream<String>> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofLines()
                );

                response.body()
                        .map(line -> {
                            try {
                                return WeatherData.fromString(line);
                            } catch (IllegalArgumentException e) {
                                return null;
                            }
                        })
                        .filter(java.util.Objects::nonNull)
                        .forEach(this::notifyObservers);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        weatherThread.start();
    }
}
