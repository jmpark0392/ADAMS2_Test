package com.rds.adams.web.opn.usr.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR002M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR002M0R0DTO;
import com.rds.adams.web.opn.usr.service.OPNUSR002M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 26.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-26 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@RestController
public class OPNUSR002M0Controller {
	
	@Autowired
	OPNUSR002M0Service oPNUSR002M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNUSR002M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNUSR002M0R0DTO> selectCsNoList(@RequestBody OPNUSR002M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNUSR002M0R0DTO> result = oPNUSR002M0Service.selectCsNoList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNUSR002M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> saveCsNo(@RequestBody OPNUSR002M0R0DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		
    	try {
    		bResult = oPNUSR002M0Service.saveCsNo(inVo);
    		
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
    	} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", e.getMessage());
		}
		
		return resultMap;
		
	}

	
}
