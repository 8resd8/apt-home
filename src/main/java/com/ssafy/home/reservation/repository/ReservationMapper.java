package com.ssafy.home.reservation.repository;

import com.ssafy.home.reservation.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {

    void insertReservation(Reservation reservation);

    void insertReservationEstate(@Param("reservationId") Long reservationId,
                                 @Param("estateId") Long estateId,
                                 @Param("memo") String memo);

    int updateReservation(Reservation reservation);


    Optional<Reservation> findReservationById(@Param("rid") Long rid);

    List<Reservation> findAllReservationsByMemberId(@Param("memberId") String memberId);

    int updateStatus(
            @Param("reservationId") Long reservationId,
            @Param("brokerId") String brokerId,
            @Param("target") String targetStatus,
            @Param("brokerMemo") String brokerMemo
    );
}
