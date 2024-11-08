package com.rds.adams.web.wrk.bat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0R0DTO;


@Mapper
public interface WRKBAT003M0DAO {
	
	public List<WRKBAT003M0R0DTO> selectList(WRKBAT003M0P0DTO inVo);

}
