package com.fd.sample.pact;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProviderServiceInstanceConfig {
    @Bean
    public ServiceInstanceListSupplier providerInstanceListSupplier() {
        return new TestServiceInstanceListSupplier("provider", 8888);
    }
}
