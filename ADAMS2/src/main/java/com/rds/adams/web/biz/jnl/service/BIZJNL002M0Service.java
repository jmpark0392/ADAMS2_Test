package com.rds.adams.web.biz.jnl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.jnl.dao.BIZJNL002M0DAO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0P1DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL002M0R0DTO;

@Service
public class BIZJNL002M0Service {
	
	@Autowired
	BIZJNL002M0DAO bIZJNL002M0DAO;
	
	public List<BIZJNL002M0R0DTO> selectList(BIZJNL002M0P0DTO inVo) {
		
		return bIZJNL002M0DAO.selectList(inVo);
		
	}
	
	public void insertList(BIZJNL002M0P1DTO inVo) {
		bIZJNL002M0DAO.insertList(inVo);
	}
	
	public void updateList(BIZJNL002M0P1DTO inVo) {
		bIZJNL002M0DAO.updateList(inVo);
	}
	
	public void deleteList(BIZJNL002M0P1DTO inVo) {
		bIZJNL002M0DAO.deleteList(inVo);
	}
}