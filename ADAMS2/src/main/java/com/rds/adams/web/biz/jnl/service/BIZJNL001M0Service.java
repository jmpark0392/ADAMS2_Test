package com.rds.adams.web.biz.jnl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.jnl.dao.BIZJNL001M0DAO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P1DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0R0DTO;

@Service
public class BIZJNL001M0Service {
	
	@Autowired
	BIZJNL001M0DAO bIZJNL001M0DAO;
	
	public List<BIZJNL001M0R0DTO> selectList(BIZJNL001M0P0DTO inVo) {
		
		return bIZJNL001M0DAO.selectList(inVo);
		
	}
	
	public void insertList(BIZJNL001M0P1DTO inVo) {
		bIZJNL001M0DAO.insertList(inVo);
	}
	
	public void updateList(BIZJNL001M0P1DTO inVo) {
		bIZJNL001M0DAO.updateList(inVo);
	}
	
	public void deleteList(BIZJNL001M0P1DTO inVo) {
		bIZJNL001M0DAO.deleteList(inVo);
	}
}