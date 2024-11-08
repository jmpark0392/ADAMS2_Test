package com.rds.adams.web.wrk.fil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.fil.dao.WRKFIL002M1DAO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1R0DTO;

@Service
public class WRKFIL002M1Service {
	
	@Autowired
	WRKFIL002M1DAO wRKFIL002M1DAO;
	
	public List<WRKFIL002M1R0DTO> selectList(WRKFIL002M1P0DTO inVo) {
		
		return wRKFIL002M1DAO.selectList(inVo);
		
	}
}