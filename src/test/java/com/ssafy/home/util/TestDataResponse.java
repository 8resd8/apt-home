package com.ssafy.home.util;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import com.ssafy.home.favorite.dto.FavoriteResponse;

public class TestDataResponse {

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(PriorityConstructorArbitraryIntrospector.INSTANCE)
            .build();


    public static FavoriteResponse favoriteResponse(String memberId, String brokerId, Long estateId) {
        return fixtureMonkey.giveMeBuilder(FavoriteResponse.class)
                .set("memberId", memberId)
                .set("estateId", estateId)
                .set("brokerId", brokerId)
                .sample();
    }
}
