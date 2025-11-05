package pl.obrona.managementapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@Profile("!test")
public class TimeConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone().withZone(ZoneId.of("Europe/Berlin"));
    }
}
