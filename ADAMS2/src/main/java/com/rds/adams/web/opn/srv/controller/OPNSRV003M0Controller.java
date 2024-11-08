package com.rds.adams.web.opn.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.opn.srv.service.OPNSRV003M0Service;

import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R1DTO;

import lombok.extern.slf4j.Slf4j;



/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 11.
 * @author : Sardor Madaminov
 * E-MAIL  : sardor@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */


@Slf4j
@RestController
public class OPNSRV003M0Controller {
	
    @Autowired
	OPNSRV003M0Service oPNSRV003M0Service;

    /**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNSRV003M0getChartInfos", method=RequestMethod.POST, consumes="application/json")
    public List<OPNSRV003M0R0DTO> getChartInfos(@RequestBody OPNSRV003M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV003M0R0DTO> result = oPNSRV003M0Service.getChartInfos(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNSRV003M0getGridData", method=RequestMethod.POST, consumes="application/json")
    public List<OPNSRV003M0R1DTO> getGridData(@RequestBody OPNSRV003M0P1DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV003M0R1DTO> result = oPNSRV003M0Service.getGridData(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}

}
