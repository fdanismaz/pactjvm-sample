package com.fd.sample.pact;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.fd.sample.pact.common.ServiceNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@Provider(ServiceNames.CATEGORY)
@PactFolder("pacts")
@WebMvcTest
public class PactProviderTest {

    @Autowired
    private MockMvc mockMvc;

    public PactProviderTest() {
    }

    @BeforeEach
    public void setup(PactVerificationContext context) {
        context.setTarget(new MockMvcTestTarget(mockMvc));
    }

    @State(value = "provider returns all categories", action = StateChangeAction.SETUP)
    public void setup() {
        // Do your necessary setup here to put the provider to the expected state
        // for the Pact provider tests
    }

    @State(value = "provider returns all categories", action = StateChangeAction.TEARDOWN)
    public void teardown() {
        // Reset your state that you set in the corresponding Setup method
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context) {
        context.verifyInteraction();
    }
}
