package com.ssafy.home.util;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.review.domain.HouseInfo;
import net.jqwik.api.Arbitraries;

import java.util.ArrayList;
import java.util.List;

public class TestDataEntity {

    private TestDataEntity() {
        // 인스턴스 생성 금지
    }

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultNotNull(true)
            .objectIntrospector(PriorityConstructorArbitraryIntrospector.INSTANCE)
            .build();

    public static Member member(String memberId) {
        return fixtureMonkey.giveMeBuilder(Member.class)
                .set("mid", memberId)
                .sample();
    }

    public static List<Member> memberMultiple(String memberIdName, int count) {
        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            members.add(fixtureMonkey.giveMeBuilder(Member.class)
                    .set("mid", memberIdName + i)
                    .sample());
        }
        return members;

    }

    public static Broker broker(String brokerId) {
        return fixtureMonkey.giveMeBuilder(Broker.class)
                .set("bid", brokerId)
                .set("address", "notToLong")
                .set("brokerName", "그래이름이다")
                .sample();
    }

    public static Estate estate(Long estateId) {
        return fixtureMonkey.giveMeBuilder(Estate.class)
                .set("eid", estateId)
                .sample();
    }

    public static Estate estate(Long estateId, String brokerId) {
        return fixtureMonkey.giveMeBuilder(Estate.class)
                .set("eid", estateId)
                .set("brokerId", brokerId)
                .sample();
    }

    public static HouseInfo houseInfo(String aptSeq) {
        return fixtureMonkey.giveMeBuilder(HouseInfo.class)
                .set("aptSeq", aptSeq)
                .sample();
    }

    public static Favorite favorite(String memberId, Long estateId) {
        return fixtureMonkey.giveMeBuilder(Favorite.class)
                .set("memberId", memberId)
                .set("estateId", estateId)
                .sample();
    }


    public static Estate estate(String brokerId, String aptSeq) {
        return fixtureMonkey.giveMeBuilder(Estate.class)
                .set("brokerId", brokerId)
                .set("aptSeq", aptSeq)
                .sample();
    }
}
