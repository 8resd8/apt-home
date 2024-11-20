package com.ssafy.home.estate.service.dto;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import org.springframework.mock.web.MockMultipartFile;

public class TestDataRequest {

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(PriorityConstructorArbitraryIntrospector.INSTANCE)
            .build();


    public static BrokerSignUpRequest brokerSignUpRequest(String brokerId) {
        return fixtureMonkey.giveMeBuilder(BrokerSignUpRequest.class)
                .set("id", brokerId)
                .set("email", brokerId + "test.com")
                .sample();
    }

    public static RegistEstateRequest registEstateRequest(String aptSeq) {
        return fixtureMonkey.giveMeBuilder(RegistEstateRequest.class)
                .set("aptSeq", aptSeq)
                .sample();
    }

    public static MockMultipartFile multipartFile() {
        return new MockMultipartFile(
                "estateImage",                // name
                "test.jpg",                   // originalFilename
                "image/jpeg",                 // contentType
                "dummy image data".getBytes() // file data
        );
    }
}
