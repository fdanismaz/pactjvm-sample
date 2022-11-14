package com.fd.sample.pact;

import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fd.sample.pact.common.ServiceNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(
        properties = {
                "server.port:0",
                "spring.cloud.consul.discovery.enabled:false"
        })
@Import(ProviderServiceInstanceConfig.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = ServiceNames.CATEGORY, port = "8888")
public class CategoryApiContractTests {

    @Autowired
    private WelcomeController welcomeController;

    @Pact(consumer = "welcome")
    public V4Pact listCategories(PactBuilder builder) {
        return builder.usingLegacyDsl().given("provider returns all categories")
                .uponReceiving("a request to GET all categories")
                .path("/category")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonArray.arrayMinLike(3)
                        .integerType("id")
                        .stringType("name")
                        .closeObject()).toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "listCategories")
    public void testListCategories() {
        String categories = welcomeController.getCategories();
        System.out.println(categories);
    }
}
