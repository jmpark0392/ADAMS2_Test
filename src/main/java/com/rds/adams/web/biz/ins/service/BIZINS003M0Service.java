package com.rds.adams.web.biz.ins.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.ins.dao.BIZINS003M0DAO;
import com.rds.adams.web.biz.ins.dto.BIZINS003M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS003M0R0DTO;
import com.rds.adams.web.core.utils.dao.ExecuteResultDAO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Service
public class BIZINS003M0Service {
	
	@Autowired
	BIZINS003M0DAO bIZINS003M0DAO;
	
	@Autowired
	ExecuteResultDAO executeResultDAO;
	
	public List<BIZINS003M0R0DTO> selectList(BIZINS003M0P0DTO inVo) {
		
		return bIZINS003M0DAO.selectList(inVo);
	}
	
	public void executeList(ExecuteDTO inVo) throws Exception {

		try {
			inVo.setBatProgId("B0003");
			inVo.setBatExeRstCd("");
			inVo.setBatExeErrCd("");
			inVo.setBatLoadStatCd("1");
			
			executeResultDAO.insertBatHist(inVo);
			
			bIZINS003M0DAO.deleteListBf(inVo);
			bIZINS003M0DAO.insertList(inVo);
			
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