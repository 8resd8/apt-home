package com.ssafy.home.reservation.repository;

import com.ssafy.home.reservation.dto.ReservationMemberResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReservationFindMapper {
    List<ReservationMemberResponse> findAllByMemberId(@Param("memberId") String memberId);
}
