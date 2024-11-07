package com.ssafy.home.reservation.repository;

import com.ssafy.home.reservation.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {
    int insertReservation(@Param("reservation") Reservation reservation);

    int updateReservation(@Param("reservation") Reservation reservation);

    int deleteReservation(@Param("rid") Long rid);

    Optional<Reservation> findReservationById(@Param("rid") Long rid);

    List<Reservation> findAllReservationsByMemberId(@Param("memberId") String memberId);
}
