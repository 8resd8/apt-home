package com.ssafy.home.util;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.dto.FavoriteResponse;

public class TestDataRequest {

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(PriorityConstructorArbitraryIntrospector.INSTANCE)
            .build();

    public static FavoriteAddRequest favoriteAddRequest(Long estateId) {
        return fixtureMonkey.giveMeBuilder(FavoriteAddRequest.class)
                .set("estateId", estateId)
                .sample();
    }


    public static FavoriteResponse favoriteResponse(String memberId, Long estateId, String brokerId) {
        return fixtureMonkey.giveMeBuilder(FavoriteResponse.class)
                .set("memberId", memberId)
                .set("estateId", estateId)
                .set("brokerId", brokerId)
                .sample();
    }
}
