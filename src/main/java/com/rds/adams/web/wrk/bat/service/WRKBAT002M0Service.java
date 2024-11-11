package com.rds.adams.web.wrk.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.bat.dao.WRKBAT002M0DAO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT002M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT002M0R0DTO;

@Service
public class WRKBAT002M0Service {
	
	@Autowired
	WRKBAT002M0DAO wRKBAT002M0DAO;
	
	public List<WRKBAT002M0R0DTO> selectList(WRKBAT002M0P0DTO inVo) {
		
		return wRKBAT002M0DAO.selectList(inVo);
		
	}
}