package com.rds.adams.web.wrk.bat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0P0DTO;
import com.rds.adams.web.wrk.bat.dto.WRKBAT003M0R0DTO;
import com.rds.adams.web.wrk.bat.service.WRKBAT003M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WRKBAT003M0Controller {
	
	@Autowired
	WRKBAT003M0Service wRKBAT003M0Service;

	@RequestMapping(value="/WRKBAT003M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<WRKBAT003M0R0DTO> select(@RequestBody WRKBAT003M0P0DTO inVo, HttpServletRequest request){
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		inVo.setCsNo(sAdamsLoginDTO.getCsNo());
		
		log.info(inVo.toString());
		
		List<WRKBAT003M0R0DTO> result = wRKBAT003M0Service.selectList(inVo);
		
		for (WRKBAT003M0R0DTO wRKBAT001M0R0DTO : result) {
			log.info(wRKBAT001M0R0DTO.toString());
		}
		
		return result;
	}

}
