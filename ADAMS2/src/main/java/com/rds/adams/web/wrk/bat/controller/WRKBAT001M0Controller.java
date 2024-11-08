package com.rds.adams.web.wrk.bat.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0P1DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT001M0R0DTO;
import com.rds.adams.web.wrk.bat.service.WRKBAT001M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class WRKBAT001M0Controller {
	
	@Autowired
	WRKBAT001M0Service wRKBAT001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/WRKBAT001M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<WRKBAT001M0R0DTO> select(@RequestBody WRKBAT001M0P0DTO inVo, HttpServletRequest request) {
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		
		List<WRKBAT001M0R0DTO> result = wRKBAT001M0Service.selectList(inVo);
		
		for (WRKBAT001M0R0DTO wRKBAT001M0R0DTO : result) {
				log.info(wRKBAT001M0R0DTO.toString());
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/WRKBAT001M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> insert(@RequestBody WRKBAT001M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			wRKBAT001M0Service.insertList(inVo);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		return resultMap;
	}

	@RequestMapping(value="/WRKBAT001M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> update(@RequestBody WRKBAT001M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			wRKBAT001M0Service.updateList(inVo);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/WRKBAT001M0DeleteList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> delete(@RequestBody WRKBAT001M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			wRKBAT001M0Service.deleteList(inVo);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		return resultMap;
	}
}
