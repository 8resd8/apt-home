package com.ssafy.home.reservation.repository;

import com.ssafy.home.reservation.domain.Reservation;
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


    Optional<Reservation> findReservationById(@Param("rid") Long rid);

    List<Reservation> findAllReservationsByMemberId(@Param("memberId") String memberId);

    int updateReservationStatusByBroker(
            @Param("reservationId") Long reservationId,
            @Param("brokerId") String brokerId,
            @Param("target") String targetStatus,
            @Param("brokerMemo") String brokerMemo
    );
}
