package ru.vladigeras.springgrpc.client;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vladigeras.springgrpc.api.CityApiServiceGrpc.CityApiServiceBlockingStub;
import ru.vladigeras.springgrpc.api.CountryApiServiceGrpc.CountryApiServiceBlockingStub;
import ru.vladigeras.springgrpc.api.*;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class SpringGrpcClientApplication {

    @GrpcClient("${api.client.name}")
    private CityApiServiceBlockingStub cityApiService;

    @GrpcClient("${api.client.name}")
    private CountryApiServiceBlockingStub countryApiService;

    public static void main(String[] args) {
        SpringApplication.run(SpringGrpcClientApplication.class, args);
    }

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            CreateCountryRs createCountryRs = countryApiService.create(CreateCountryRq.newBuilder().setTitle(random(30)).build());
            long countryId = createCountryRs.getId();
            log.info("Country was created, id = {}", countryId);
            CreateCityRs createCityRs = cityApiService.create(CreateCityRq.newBuilder().setTitle(random(10)).setCountryId(countryId).build());
            log.info("City was created, id = {}", createCityRs.getId());
            log.info("Cities were selected");
            cityApiService.find(FindCityRq.newBuilder().setCityTitle("a").setCountryId(countryId).build()).getCitiesList().forEach(c -> {
                log.info("City: {}", c);
            });
        };
    }

    private String random(int size) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
