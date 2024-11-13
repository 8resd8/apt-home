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

    public static String userMassageSummary(Member member, String memberMassage) {
        return String.format(
                """
                        - member: %s
                        A member selected a real estate agent to sell and lease apartments, and this is a review left by that member.
                        I left a review like below, so please summarize it into about 20 lines Write only the body. 
                        For Korean
                        - memberMassage: %s
                        """,
                member.getName(),
                memberMassage
        );
    }

    public static String brokerReviewGenerator(String memberMassage) {
        return String.format(
                """
                        For apartment sales and lease transactions, I showed the apartment sales to the members through door-to-door reservations.
                        The review left by the member is below.
                        Write a sincere reply to this review For Korean
                        [member massage]
                        %s
                        """,
                memberMassage
        );
    }
}
