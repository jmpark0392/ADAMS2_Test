package com.rds.adams.web.biz.adt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.adt.dao.BIZADT001M0DAO;
import com.rds.adams.web.biz.adt.dto.BIZADT001M0P0DTO;
import com.rds.adams.web.biz.adt.dto.BIZADT001M0R0DTO;
import com.rds.adams.web.core.utils.dao.ExecuteResultDAO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Service
public class BIZADT001M0Service {
	
	@Autowired
	BIZADT001M0DAO bIZADT001M0DAO;
	
	@Autowired
	ExecuteResultDAO executeResultDAO;
	
	public List<BIZADT001M0R0DTO> selectList(BIZADT001M0P0DTO inVo) {
		
		return bIZADT001M0DAO.selectList(inVo);
	}
	
	public void executeList(ExecuteDTO inVo) throws Exception {
		
		try {		
			inVo.setBatProgId("B0006");
			inVo.setBatExeRstCd("");
			inVo.setBatExeErrCd("");
			inVo.setBatLoadStatCd("1");
			
			executeResultDAO.insertBatHist(inVo);
			
			bIZADT001M0DAO.deleteListBf(inVo);
			bIZADT001M0DAO.insertList(inVo);
			
			inVo.setBatLoadStatCd("0");
			inVo.setBatExeRstCd("OK");
			executeResultDAO.updateBatHist(inVo);
		} catch ( Exception e ) {
			inVo.setBatLoadStatCd("9");
			inVo.setBatExeRstCd("ERROR");
			executeResultDAO.updateBatHist(inVo);
			throw new Exception(e.getMessage());
		}		
	return;
	}
}