package com.rds.adams.web.biz.ins.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.ins.dao.BIZINS002M0DAO;
import com.rds.adams.web.biz.ins.dto.BIZINS002M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS002M0R0DTO;
import com.rds.adams.web.core.utils.dao.ExecuteResultDAO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Service
public class BIZINS002M0Service {
	
	@Autowired
	BIZINS002M0DAO bIZINS002M0DAO;
	
	@Autowired
	ExecuteResultDAO executeResultDAO;
	
	public List<BIZINS002M0R0DTO> selectList(BIZINS002M0P0DTO inVo) {
		return bIZINS002M0DAO.selectList(inVo);
	}
	
	public void executeList(ExecuteDTO inVo) throws Exception {
		
		try {
			inVo.setBatProgId("B0002");
			inVo.setBatExeRstCd("");
			inVo.setBatExeErrCd("");
			inVo.setBatLoadStatCd("1");
			
			executeResultDAO.insertBatHist(inVo);
			
			bIZINS002M0DAO.deleteListBf(inVo);
			bIZINS002M0DAO.insertList(inVo);
			
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