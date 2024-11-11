package com.rds.adams.web.wrk.fil.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1R0DTO;

@Mapper
public interface WRKFIL002M1DAO {
	
	public List<WRKFIL002M1R0DTO> selectList(WRKFIL002M1P0DTO inVo);

}