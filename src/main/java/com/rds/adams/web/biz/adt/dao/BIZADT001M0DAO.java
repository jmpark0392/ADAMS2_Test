package com.rds.adams.web.biz.adt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.biz.adt.dto.BIZADT001M0P0DTO;
import com.rds.adams.web.biz.adt.dto.BIZADT001M0R0DTO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Mapper
public interface BIZADT001M0DAO {
	
	public List<BIZADT001M0R0DTO> selectList(BIZADT001M0P0DTO inVo);
	
	public void insertList(ExecuteDTO inVo);
	
	public void deleteListBf(ExecuteDTO inVo);
	
}