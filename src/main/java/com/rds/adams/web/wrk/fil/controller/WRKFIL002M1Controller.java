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
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M1R0DTO;
import com.rds.adams.web.wrk.fil.service.WRKFIL002M1Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class WRKFIL002M1Controller {
	
	@Autowired
	WRKFIL002M1Service wRKFIL002M1Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/WRKFIL002M1SelectList", method=RequestMethod.POST)
	public Map<String, Object> select(@RequestBody WRKFIL002M1P0DTO inVo, HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		log.info(inVo.toString());
		
		int totalRowCnt = wRKFIL002M1Service.selectListCount(inVo); 

		List<WRKFIL002M1R0DTO> resultList = wRKFIL002M1Service.selectList(inVo);

		for (WRKFIL002M1R0DTO wRKFIL002M1R0DTO : resultList) {
				log.info(wRKFIL002M1R0DTO.toString());
		}
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", totalRowCnt);
		resultMap.put("resultPage", inVo.getPageNum());

		return resultMap;
		
	}

}
