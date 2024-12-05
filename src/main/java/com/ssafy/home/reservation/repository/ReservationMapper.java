package com.ssafy.home.reservation.repository;

import com.ssafy.home.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {

    void insertReservationByMember(Reservation reservation);

    void insertReservationEstateByBroker(@Param("reservationId") Long reservationId,
                                         @Param("estateId") Long estateId,
                                         @Param("memo") String memo);

    int updateReservationByMember(Reservation reservation);


    Optional<ReservationResponse> findReservationById(@Param("rid") Long rid);

    List<ReservationResponse> findAllReservationsByMemberId(@Param("memberId") String memberId);

    int updateReservationStatusByBroker(
            @Param("reservationId") Long reservationId,
            @Param("brokerId") String brokerId,
            @Param("target") String targetStatus,
            @Param("brokerMemo") String brokerMemo
    );

    List<ReservationResponse> findAllReservationsByBrokerId(String brokerId);
}
