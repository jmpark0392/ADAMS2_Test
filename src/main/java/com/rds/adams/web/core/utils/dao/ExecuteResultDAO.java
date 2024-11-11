package com.rds.adams.web.core.utils.dao;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Mapper
public interface ExecuteResultDAO {
	
	public void insertBatHist(ExecuteDTO inVo);
	public void updateBatHist(ExecuteDTO inVo);
	
}