package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponseDto;
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

    @Select("SELECT e.eid, e.type, e.status, e.floor, e.total_floor, e.area, e.amount, e.desc, "+
            "e.broker_id, b.office_name, b.phone_num, b.address, b.license_num, " +
            "h.apt_nm, h.build_year, h.road_nm, h.road_nm_bonbun "+
            "FROM broker b, estate e, houseinfos h " +
            "WHERE e.eid = #{eid} " +
            "AND e.apt_seq = h.apt_seq " +
            "AND b.bid = e.broker_id"
    )
    EstateDetailResponseDto selectEstateDetail(@Param("eid") Long id);

    @Select("SELECT * FROM estate " +
        "WHERE eid = #{id} "
    )
    Estate selectEstate(@Param("id") Long id);
}
