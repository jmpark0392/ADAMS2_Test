package com.rds.adams.web.biz.ins.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.biz.ins.dto.BIZINS001M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS001M0R0DTO;
import com.rds.adams.web.biz.ins.service.BIZINS001M0Service;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.core.utils.dto.ExecuteDTO;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class BIZINS001M0Controller {
	
	@Autowired
	BIZINS001M0Service bIZINS001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/BIZINS001M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<BIZINS001M0R0DTO> select(@RequestBody BIZINS001M0P0DTO inVo, HttpServletRequest request) {
		
		log.info(inVo.toString());
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		List<BIZINS001M0R0DTO> result = bIZINS001M0Service.selectList(inVo);
		
		for (BIZINS001M0R0DTO bIZINS001M0Service : result) {
				log.info(bIZINS001M0Service.toString());
		}
		return result;	
	}
	
	@RequestMapping(value="/BIZINS001M0ExecuteList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> execute(@RequestBody ExecuteDTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		
		log.info(inVo.toString());
		try {
			bIZINS001M0Service.executeList(inVo);
			log.info("success");
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "Error : " + e.getMessage());
		}
		return resultMap;
	}
}
