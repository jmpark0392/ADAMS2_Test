package com.rds.adams.web.biz.jnl.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P0DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0P1DTO;
import com.rds.adams.web.biz.jnl.dto.BIZJNL001M0R0DTO;
import com.rds.adams.web.biz.jnl.service.BIZJNL001M0Service;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class BIZJNL001M0Controller {
	
	@Autowired
	BIZJNL001M0Service bIZJNL001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/BIZJNL001M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<BIZJNL001M0R0DTO> select(@RequestBody BIZJNL001M0P0DTO inVo, HttpServletRequest request) {
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		
		List<BIZJNL001M0R0DTO> result = bIZJNL001M0Service.selectList(inVo);
		
		for (BIZJNL001M0R0DTO bIZJNL001M0Service : result) {
				log.info(bIZJNL001M0Service.toString());
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/BIZJNL001M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> insert(@RequestBody BIZJNL001M0P1DTO inVo, HttpServletRequest request) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			bIZJNL001M0Service.insertList(inVo);
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

	@RequestMapping(value="/BIZJNL001M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> update(@RequestBody BIZJNL001M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			bIZJNL001M0Service.updateList(inVo);
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
	
	@RequestMapping(value="/BIZJNL001M0DeleteList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> delete(@RequestBody BIZJNL001M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
			bIZJNL001M0Service.deleteList(inVo);
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
