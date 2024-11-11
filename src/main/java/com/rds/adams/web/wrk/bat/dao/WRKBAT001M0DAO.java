package com.rds.adams.web.wrk.bat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P1DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0R0DTO;

@Mapper
public interface WRKBAT001M0DAO {
	
	public List<WRKBAT001M0R0DTO> selectList(WRKBAT001M0P0DTO inVo);
	
	public void insertList(WRKBAT001M0P1DTO inVo);
	
	public void updateList(WRKBAT001M0P1DTO inVo);
	
	public void deleteList(WRKBAT001M0P1DTO inVo);
	
}