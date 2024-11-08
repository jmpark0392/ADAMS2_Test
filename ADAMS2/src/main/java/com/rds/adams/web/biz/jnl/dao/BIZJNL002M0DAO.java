package com.rds.adams.web.biz.jnl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0P1DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0R0DTO;

@Mapper
public interface BIZJNL002M0DAO {
	
	public List<BIZJNL002M0R0DTO> selectList(BIZJNL002M0P0DTO inVo);
	
	public void insertList(BIZJNL002M0P1DTO inVo);
	
	public void updateList(BIZJNL002M0P1DTO inVo);
	
	public void deleteList(BIZJNL002M0P1DTO inVo);
	
}