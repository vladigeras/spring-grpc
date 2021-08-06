package ru.vladigeras.springgrpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
public class SpringGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGrpcServerApplication.class, args);
    }

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
