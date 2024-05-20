package com.nicolacalise.SportAccountingManager.controllers.proxies;

import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import com.nicolacalise.SportAccountingManager.services.httpServices.HttpWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("proxy/")
public class WeatherController {

    @Autowired
    private HttpWeatherService httpWeatherService;

    @GetMapping("info")
    public ResponseEntity<Message> info(){
        return ResponseEntity.ok(new ConfirmMessage(200, "STATUS OK", 1));
    }

    /**
     * @ERROR: 403 Forbidden -> redirect forbidden caused by security configuration
     */
    @GetMapping("weather")
    public Mono<String> getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return httpWeatherService.getWeatherForecast(latitude, longitude);
    }

}
