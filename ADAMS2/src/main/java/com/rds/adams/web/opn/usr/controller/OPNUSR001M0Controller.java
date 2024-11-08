package com.rds.adams.web.opn.usr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P1DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0R0DTO;
import com.rds.adams.web.opn.usr.service.OPNUSR001M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 11.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@RestController
public class OPNUSR001M0Controller {
	
	@Autowired
	OPNUSR001M0Service OPNUSR001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNUSR001M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNUSR001M0R0DTO> selectMenuList(@RequestBody OPNUSR001M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNUSR001M0R0DTO> result = OPNUSR001M0Service.selectMenuList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNUSR001M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public void insertList(@RequestBody OPNUSR001M0P1DTO inVo, HttpServletRequest request) {
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		String usrEmail = sAdamsLoginDTO.getUsrId();
		
		inVo.setUsrId(usrEmail.split("@")[0]);
		
		log.info(inVo.toString());
		try {
			OPNUSR001M0Service.insertList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		
		return;
	}
	
	@RequestMapping(value="/OPNUSR001M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public void updateList(@RequestBody OPNUSR001M0P1DTO inVo, HttpServletRequest request) throws Exception {
		
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		String usrEmail = sAdamsLoginDTO.getUsrId();
		
		inVo.setUsrId(usrEmail.split("@")[0]);
		
		log.info(inVo.toString());
		try {
			OPNUSR001M0Service.updateList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		
		return;
		
	}

	@RequestMapping(value="/OPNUSR001M0DeleteList", method=RequestMethod.POST, consumes="application/json")
	public void deleteList(@RequestBody OPNUSR001M0P1DTO inVo) throws Exception {
		
		log.info(inVo.toString());
		try {
			OPNUSR001M0Service.deleteList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		
		return;
		
	}
	
}
