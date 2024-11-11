package com.rds.adams.web.biz.ins.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.biz.ins.dto.BIZINS002M0P0DTO;
import com.rds.adams.web.biz.ins.dto.BIZINS002M0R0DTO;
import com.rds.adams.web.biz.ins.service.BIZINS002M0Service;
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
public class BIZINS002M0Controller {
	
	@Autowired
	BIZINS002M0Service bIZINS002M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/BIZINS002M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<BIZINS002M0R0DTO> select(@RequestBody BIZINS002M0P0DTO inVo, HttpServletRequest request) {
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setStdYymm(inVo.getStdYymm());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		
		List<BIZINS002M0R0DTO> result = bIZINS002M0Service.selectList(inVo);
		
		for (BIZINS002M0R0DTO bIZINS002M0Service : result) {
				log.info(bIZINS002M0Service.toString());
		}
		return result;
	}
	
	@RequestMapping(value="/BIZINS002M0ExecuteList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> execute(@RequestBody ExecuteDTO inVo, HttpServletRequest request) {
		

		HashMap<String, Object> resultMap = new HashMap<String, Object>();		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		
		inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		
		try {
			bIZINS002M0Service.executeList(inVo);
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
