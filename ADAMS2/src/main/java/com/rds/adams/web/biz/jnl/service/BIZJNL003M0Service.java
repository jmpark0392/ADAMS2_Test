package com.rds.adams.web.biz.jnl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.biz.jnl.dao.BIZJNL003M0DAO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL003M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL003M0R0DTO;
import com.rds.adams.web.core.utils.dao.ExecuteResultDAO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;

@Service
public class BIZJNL003M0Service {
	
	@Autowired
	BIZJNL003M0DAO bIZJNL003M0DAO;
	
	@Autowired
	ExecuteResultDAO executeResultDAO;
	
	public List<BIZJNL003M0R0DTO> selectList(BIZJNL003M0P0DTO inVo) {
		
	return bIZJNL003M0DAO.selectList(inVo);
	}
	
	public void executeList(ExecuteDTO inVo) throws Exception {
		
		try {
			inVo.setBatProgId("B0004");
			inVo.setBatExeRstCd("");
			inVo.setBatExeErrCd("");
			inVo.setBatLoadStatCd("1");
			
			executeResultDAO.insertBatHist(inVo);
			
			bIZJNL003M0DAO.deleteListBf(inVo);
			bIZJNL003M0DAO.insertList(inVo);
			
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