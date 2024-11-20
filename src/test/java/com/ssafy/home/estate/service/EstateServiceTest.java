package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.service.AuthFacade;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.global.enums.EstateType;
import com.ssafy.home.global.repository.UtilMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class EstateServiceTest {

    @Autowired
    private EstateService estateService;

    @Autowired
    private AuthFacade authService;

    @Autowired
    private BrokerMapper brokerMapper;

    @Autowired
    private UtilMapper utilMapper;

    private Broker broker;
    private String brokerId = "broker1";
    private MockMultipartFile multipartFile = new MockMultipartFile("123", new byte[]{});


    @BeforeEach
    void setUp() {
        BrokerSignUpRequest brokerSignUpRequest = new BrokerSignUpRequest(
                brokerId,
                "password456",
                "Test Office",
                "Jane Doe",
                "010-8765-4321",
                "Seoul",
                "123-45-6789",
                "broker@test.com"
        );

        authService.signUpBroker(brokerSignUpRequest, multipartFile);

        broker = brokerMapper.findById(brokerId).get();
    }

    @DisplayName("매물 정상 등록")
    @Test
    public void postEstate() {
        //given
        RegistEstateRequest requestDto = new RegistEstateRequest(
                "11110-100",
                EstateType.매매,
                "200000",
                3,
                7,
                5.4,
                "테스트 매물입니다."
        );

        //when
        estateService.createEstate(requestDto, broker);
        Long createdId = utilMapper.selectLastInsertId();
        //then

        Assertions.assertNotNull(createdId);
        Assertions.assertNotNull(estateService.findEstateById(createdId));
    }

    @DisplayName("매물 상세 조회")
    @Test
    public void getEstate() {
        //given
        RegistEstateRequest requestDto = new RegistEstateRequest(
                "11110-100",
                EstateType.매매,
                "200000",
                3,
                7,
                5.4,
                "테스트 매물입니다."
        );
        estateService.createEstate(requestDto, broker);
        Long createdId = utilMapper.selectLastInsertId();

        //when
        EstateDetailResponse responseDto = estateService.findEstateDetailById(createdId);

        //then
        Assertions.assertNotNull(responseDto);
    }
}


