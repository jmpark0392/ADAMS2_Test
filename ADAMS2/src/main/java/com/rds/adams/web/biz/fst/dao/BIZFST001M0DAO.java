package com.rds.adams.web.biz.fst.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.biz.fst.dto.BIZFST001M0P0DTO;
import com.rds.adams.web.biz.fst.dto.BIZFST001M0R0DTO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Mapper
public interface BIZFST001M0DAO {
	
	public List<BIZFST001M0R0DTO> selectList(BIZFST001M0P0DTO inVo);
	
	public void deleteListBf(ExecuteDTO inVo);
	
	public void insertList(ExecuteDTO inVo);
}