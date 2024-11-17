package com.ssafy.home.util;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.dto.FavoriteResponse;

public class TestDataEntity {

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(PriorityConstructorArbitraryIntrospector.INSTANCE)
            .build();

    public static Member member(String memberId) {
        return fixtureMonkey.giveMeBuilder(Member.class)
                .set("mid", memberId)
                .sample();
    }


    public static Favorite favorite(String memberId, Long estateId) {
        return fixtureMonkey.giveMeBuilder(Favorite.class)
                .set("memberId", memberId)
                .set("estateId", estateId)
                .sample();
    }
}
