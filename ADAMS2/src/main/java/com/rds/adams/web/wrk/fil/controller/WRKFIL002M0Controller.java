package com.rds.adams.web.wrk.fil.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0P1DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL002M0R0DTO;
import com.rds.adams.web.wrk.fil.service.WRKFIL002M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class WRKFIL002M0Controller {
	
	@Autowired
	WRKFIL002M0Service wRKFIL002M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/WRKFIL002M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<WRKFIL002M0R0DTO> select(@RequestBody WRKFIL002M0P0DTO inVo, HttpServletRequest request) {
		
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());

		List<WRKFIL002M0R0DTO> result = wRKFIL002M0Service.selectList(inVo);


		for (WRKFIL002M0R0DTO wRKFIL002M0R0DTO : result) {
				log.info(wRKFIL002M0R0DTO.toString());
		}

		return result;
		
	}
	
	@RequestMapping(value="/WRKFIL002M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> insert(@RequestBody WRKFIL002M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		
		log.info(inVo.toString());
		try {
			wRKFIL002M0Service.insertList(inVo);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		return resultMap;
	}

	@RequestMapping(value="/WRKFIL002M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> update(@RequestBody WRKFIL002M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		inVo.setFrstRegEmpNo(sAdamsLoginDTO.getUsrId());
		
		log.info(inVo.toString());
		try {
			wRKFIL002M0Service.updateList(inVo);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/WRKFIL002M0DeleteList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> delete(@RequestBody WRKFIL002M0P1DTO inVo, HttpServletRequest request) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		try {
				wRKFIL002M0Service.deleteList(inVo);
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
	return resultMap;
	}
}
