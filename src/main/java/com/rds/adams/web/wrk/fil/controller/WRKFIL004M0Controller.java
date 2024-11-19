package com.rds.adams.web.wrk.fil.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL004M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL004M0R0DTO;
import com.rds.adams.web.wrk.fil.service.WRKFIL004M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class WRKFIL004M0Controller {
	
	@Autowired
	WRKFIL004M0Service wRKFIL004M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/WRKFIL004M0SelectList", method=RequestMethod.POST)
	public Map<String, Object> select(@RequestBody WRKFIL004M0P0DTO inVo, HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		log.info(inVo.toString());
		
		int totalRowCnt = wRKFIL004M0Service.selectListCount(inVo);
		
		List<WRKFIL004M0R0DTO> resultList = wRKFIL004M0Service.selectList(inVo);
		
		for (WRKFIL004M0R0DTO wRKFIL004M0R0DTO : resultList) {
				log.info(wRKFIL004M0R0DTO.toString());
		}
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", totalRowCnt);
		resultMap.put("resultPage", inVo.getPageNum());
		
		return resultMap;
		
	}

}
