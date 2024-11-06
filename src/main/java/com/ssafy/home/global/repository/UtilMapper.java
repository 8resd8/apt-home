package com.ssafy.home.global.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UtilMapper {

    @Select("SELECT LAST_INSERT_ID()")
    Long selectLastInsertId();
}
