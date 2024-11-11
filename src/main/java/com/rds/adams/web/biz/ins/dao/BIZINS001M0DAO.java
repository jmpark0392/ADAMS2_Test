package com.rds.adams.web.biz.ins.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.biz.ins.dto.BIZINS001M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS001M0R0DTO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Mapper
public interface BIZINS001M0DAO {
	
	public List<BIZINS001M0R0DTO> selectList(BIZINS001M0P0DTO inVo);
	
	public void insertList(ExecuteDTO inVo);
	
	public void deleteListBf(ExecuteDTO inVo);
	
}