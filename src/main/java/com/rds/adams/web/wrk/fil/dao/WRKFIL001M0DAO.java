package com.rds.adams.web.wrk.fil.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.wrk.fil.dto.WRKFIL001M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL001M0P1DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL001M0R0DTO;

@Mapper
public interface WRKFIL001M0DAO {
	
	public List<WRKFIL001M0R0DTO> selectList(WRKFIL001M0P0DTO inVo);
	
	public void insertList(WRKFIL001M0P1DTO inVo);
	
	public void updateList(WRKFIL001M0P1DTO inVo);
	
	public void deleteList(WRKFIL001M0P1DTO inVo);
}