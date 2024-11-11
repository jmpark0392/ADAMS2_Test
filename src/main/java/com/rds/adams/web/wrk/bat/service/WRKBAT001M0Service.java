package com.rds.adams.web.wrk.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.bat.dao.WRKBAT001M0DAO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P1DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0R0DTO;

@Service
public class WRKBAT001M0Service {
	
	@Autowired
	WRKBAT001M0DAO wRKBAT001M0DAO;
	
	public List<WRKBAT001M0R0DTO> selectList(WRKBAT001M0P0DTO inVo) {
		
		return wRKBAT001M0DAO.selectList(inVo);
		
	}
	
	public void insertList(WRKBAT001M0P1DTO inVo) {
		wRKBAT001M0DAO.insertList(inVo);
	}
	
	public void updateList(WRKBAT001M0P1DTO inVo) {
		wRKBAT001M0DAO.updateList(inVo);
	}
	
	public void deleteList(WRKBAT001M0P1DTO inVo) {
		wRKBAT001M0DAO.deleteList(inVo);
	}
}