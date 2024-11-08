package com.rds.adams.web.wrk.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.bat.dao.WRKBAT003M0DAO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0R0DTO;

@Service
public class WRKBAT003M0Service {
	
	@Autowired
	WRKBAT003M0DAO wRKBAT003M0DAO;

	public List<WRKBAT003M0R0DTO> selectList(WRKBAT003M0P0DTO inVo) {
	
		return wRKBAT003M0DAO.selectList(inVo);
	}

}
