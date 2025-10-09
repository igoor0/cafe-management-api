package pl.obrona.managementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApiApplication.class, args);
    }

}
