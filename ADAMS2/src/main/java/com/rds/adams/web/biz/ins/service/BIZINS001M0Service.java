package com.rds.adams.web.biz.ins.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.ins.dao.BIZINS001M0DAO;
import com.rds.adams.web.biz.ins.dto.BIZINS001M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS001M0R0DTO;
import com.rds.adams.web.core.utils.dao.ExecuteResultDAO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Service
public class BIZINS001M0Service {
	
	@Autowired
	BIZINS001M0DAO bIZINS001M0DAO;
	
	@Autowired
	ExecuteResultDAO executeResultDAO;
	
	public List<BIZINS001M0R0DTO> selectList(BIZINS001M0P0DTO inVo) {	
		return bIZINS001M0DAO.selectList(inVo);
	}
	
	public void executeList(ExecuteDTO inVo) throws Exception {

		try {
			inVo.setBatProgId("B0001");
			inVo.setBatExeRstCd("");
			inVo.setBatExeErrCd("");
			inVo.setBatLoadStatCd("1");
			
			executeResultDAO.insertBatHist(inVo);
			
			bIZINS001M0DAO.deleteListBf(inVo);
			bIZINS001M0DAO.insertList(inVo);
			
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