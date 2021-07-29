package ru.vladigeras.springgrpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vladigeras.springgrpc.service.CityService;
import ru.vladigeras.springgrpc.service.CountryService;
import ru.vladigeras.springgrpc.util.Logger;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringGrpcApplication {

    private final CountryService countryService;
    private final CityService cityService;

    public static void main(String[] args) {
        SpringApplication.run(SpringGrpcApplication.class, args);
    }

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            log.info("Cities: {}", Logger.toJson(cityService.find("oST", 2L)));
        };
    }
}
