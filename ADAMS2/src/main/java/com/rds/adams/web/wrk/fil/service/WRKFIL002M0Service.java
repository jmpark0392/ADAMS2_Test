package com.rds.adams.web.wrk.fil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.wrk.fil.dao.WRKFIL002M0DAO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0P1DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0R0DTO;

@Service
public class WRKFIL002M0Service {
	
	@Autowired
	WRKFIL002M0DAO wRKFIL002M0DAO;
	
	public List<WRKFIL002M0R0DTO> selectList(WRKFIL002M0P0DTO inVo) {

		return wRKFIL002M0DAO.selectList(inVo);
		
	}
	
	public void insertList(WRKFIL002M0P1DTO inVo) {
		wRKFIL002M0DAO.insertList(inVo);
	}
	
	public void updateList(WRKFIL002M0P1DTO inVo) {
		wRKFIL002M0DAO.updateList(inVo);
	}
	
	public void deleteList(WRKFIL002M0P1DTO inVo) {
		wRKFIL002M0DAO.deleteList(inVo);
	}
}