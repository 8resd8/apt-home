package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.RegistEstateRequestDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EstateMapper {

    @Insert("INSERT INTO estate " +
            "(broker_id, apt_seq, type, floor, total_floor, area, amount, `desc`) " +
            "VALUES " +
            "(#{brokerId}, #{brokerEstate.aptSeq}, #{brokerEstate.type}, " +
            "#{brokerEstate.floor}, #{brokerEstate.totalFloor}, #{brokerEstate.area}, " +
            "#{brokerEstate.amount}, #{brokerEstate.desc})")
    void insertBrokerEstate(@Param("brokerEstate") RegistEstateRequestDto requestDto, @Param("brokerId") String brokerId);
}
