package com.rds.adams.web.mst.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0R0DTO;

@Mapper
public interface MSTSYS001M0DAO {
	
	public List<MSTSYS001M0R0DTO> selectList(MSTSYS001M0P0DTO inVo);

	public void insertList(MSTSYS001M0P1DTO inVo);
	
	public void updateList(MSTSYS001M0P1DTO inVo);
}