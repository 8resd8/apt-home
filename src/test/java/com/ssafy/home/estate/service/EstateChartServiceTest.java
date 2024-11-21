package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.service.AuthFacade;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.service.dto.TestDataRequest;
import com.ssafy.home.global.repository.UtilMapper;
import com.ssafy.home.review.domain.HouseInfo;
import com.ssafy.home.util.TestDataEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class EstateChartServiceTest {

    @Autowired
    private EstateService estateService;

    @Autowired
    private AuthFacade authService;

    @Autowired
    private BrokerMapper brokerMapper;

    @Autowired
    private UtilMapper utilMapper;

    private Broker broker;
    private Estate estate;
    private String brokerId = "broker1";
    private MockMultipartFile[] multipartFiles = TestDataRequest.multipartFiles(10);
    private MockMultipartFile multipartFile = TestDataRequest.multipartFile();
    private BrokerSignUpRequest BrokerSignUpRequest;
    private RegistEstateRequest registEstateRequest;
    private String aptSeq = "11110-110";
    private Long estateId = 1L;
    private HouseInfo houseInfo;


    @BeforeEach
    void setUp() {
        broker = TestDataEntity.broker(brokerId);
        houseInfo = TestDataEntity.houseInfo(aptSeq);
        estate = TestDataEntity.estate(brokerId, aptSeq);

        BrokerSignUpRequest = TestDataRequest.brokerSignUpRequest(brokerId);
        registEstateRequest = TestDataRequest.registEstateRequest(aptSeq);
        authService.signUpBroker(BrokerSignUpRequest, multipartFile);


    }

    @DisplayName("매물 정상 등록")
    @Test
    public void postEstate() {
        estateService.createEstate(broker, registEstateRequest, multipartFiles);
        Long l = utilMapper.selectLastInsertId();
        Assertions.assertNotNull(estateService.findEstateById(estateId));
    }

    @DisplayName("매물 상세 조회")
    @Test
    public void getEstate() {
        estateService.createEstate(broker, registEstateRequest, multipartFiles);

        EstateDetailResponse actualResponse = estateService.findEstateDetailById(estateId);

        Assertions.assertNotNull(actualResponse);
    }
}


