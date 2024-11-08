package com.rds.adams.web.biz.jnl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P1DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0R0DTO;

@Mapper
public interface BIZJNL001M0DAO {
	
	public List<BIZJNL001M0R0DTO> selectList(BIZJNL001M0P0DTO inVo);
	
	public void insertList(BIZJNL001M0P1DTO inVo);
	
	public void updateList(BIZJNL001M0P1DTO inVo);
	
	public void deleteList(BIZJNL001M0P1DTO inVo);
	
}