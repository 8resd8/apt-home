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

    int updateReserveStatus(@Param("reservationId") Long reservationId, @Param("brokerId") String brokerId,
                            @Param("status") String status,
                            @Param("brokerMemo") String brokerMemo,
                            @Param("condition") String condition
    );

    int updateCompleteStatus(@Param("reservationId") Long reservationId,
                             @Param("brokerId") String brokerId,
                             @Param("status") String status,
                             @Param("condition") String condition
    );
}
