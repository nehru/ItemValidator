package com.example.itemvalidator.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusSetup {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> commonTags(){
        return registry -> registry.config().commonTags("application", "Item-Validator-Application");
    }

}
