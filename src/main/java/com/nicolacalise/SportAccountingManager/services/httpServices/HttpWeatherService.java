package com.nicolacalise.SportAccountingManager.services.httpServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Service
public class HttpWeatherService {

    @Autowired
    private WebClient webClient;

    @Value("${weather.default.uri}")
    private String weather_uri;

    public Mono<String> getWeatherForecast(double latitude, double longitude) {
        String params = String.format(Locale.US,
                "?latitude=%f&longitude=%f&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m",
                latitude, longitude);

        String url = weather_uri + params;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }

}
