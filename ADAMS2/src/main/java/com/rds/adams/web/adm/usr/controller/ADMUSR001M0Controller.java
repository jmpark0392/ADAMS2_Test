package com.rds.adams.web.adm.usr.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P0DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P1DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0R0DTO;
import com.rds.adams.web.adm.usr.service.ADMUSR001M0Service;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 10.
 * @author : PARK JUNMIN
 * E-MAIL  : jm.park@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@RestController
public class ADMUSR001M0Controller {
	
	@Autowired
	ADMUSR001M0Service aDMUSR001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ADMUSR001M0SelectCompList", method=RequestMethod.POST, consumes="application/json")
	public List<ADMUSR001M0R0DTO> selectCompList(@RequestBody ADMUSR001M0P0DTO inVo, HttpServletRequest request) {
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		List<ADMUSR001M0R0DTO> result = aDMUSR001M0Service.selectCompList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}

	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */	
	
	@RequestMapping(value="/ADMUSR001M0UpdateCompList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> update(@RequestBody ADMUSR001M0P1DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;

		log.info(inVo.toString());
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFnlUpdEmpNo(sAdamsLoginDTO.getUsrId());
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
    	
    	bResult = aDMUSR001M0Service.updateCompList(inVo);
		
		if (bResult) {
			
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} else {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "Update Failed !!!");
		}

		return resultMap;
	}

	/*
	@RequestMapping(value="/ADMUSR001M0UpdateCompList", method=RequestMethod.POST, consumes="application/json")
	public List<ADMUSR001M0R0DTO> updateCompList(@RequestBody ADMUSR001M0P1DTO inVo, HttpServletRequest request) {
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		List<ADMUSR001M0R0DTO> result = aDMUSR001M0Service.updateCompList(inVo);
		
		log.info(result.toString());
		
		return result;

	
	}
	*/	
	
	@RequestMapping(value="/ADMUSR001M0UpdateServiceCode", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> updateService(@RequestBody ADMUSR001M0P1DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;

		log.info(inVo.toString());
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setFnlUpdEmpNo(sAdamsLoginDTO.getUsrId());
    	inVo.setCsNo(sAdamsLoginDTO.getCsNo());
    	
    	bResult = aDMUSR001M0Service.updateServiceCode(inVo);
		
		if (bResult) {
			
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "Success !!!");
		} else {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "Update Failed !!!");
		}

		return resultMap;
	}
}
