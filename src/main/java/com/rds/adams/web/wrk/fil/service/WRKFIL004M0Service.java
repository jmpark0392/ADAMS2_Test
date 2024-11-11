package com.rds.adams.web.wrk.fil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.fil.dao.WRKFIL004M0DAO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL004M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL004M0R0DTO;

@Service
public class WRKFIL004M0Service {
	
	@Autowired
	WRKFIL004M0DAO wRKFIL004M0DAO;
	
	public List<WRKFIL004M0R0DTO> selectList(WRKFIL004M0P0DTO inVo) {
		
		return wRKFIL004M0DAO.selectList(inVo);
		
	}

}