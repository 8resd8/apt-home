package com.ssafy.home.global.util;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.domain.HouseInfo;

public class PromptGenerator {

    public static String generateReviewPrompt(Member member, HouseInfo houseInfo) {
        return String.format(
                """
                        Imagine you are writing a thoughtful review for a real estate agent after visiting the apartment below.
                        Express genuine impressions and appreciation for the agent's assistance during the visit.
                        Details:
                        - Apartment: %s
                        - Address: %s, %s, %s
                        - Built in: %d
                        - Location: %s, %s
                        - Visitor: %s
                        
                        Mention the apartment's atmosphere, any unique aspects that stood out, and convey your feelings about the overall experience in a friendly and grateful tone, within 30-50 words. For Korean
                        """,
                houseInfo.getAptNm(),
                houseInfo.getRoadNm(),
                houseInfo.getRoadNmBonbun(),
                houseInfo.getUmdNm(),
                houseInfo.getBuildYear(),
                houseInfo.getLatitude(),
                houseInfo.getLongitude(),
                member.getName()
        );
    }
}
