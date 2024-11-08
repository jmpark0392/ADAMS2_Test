package com.rds.adams.web.opn.srv.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P2DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R2DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R3DTO;
import com.rds.adams.web.opn.srv.service.OPNSRV002M0Service;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 21.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-21 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@RestController
public class OPNSRV002M0Controller {
	
	@Autowired
	OPNSRV002M0Service OPNSRV002M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNSRV002M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNSRV002M0R0DTO> selectOptList(@RequestBody OPNSRV002M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV002M0R0DTO> result = OPNSRV002M0Service.selectOptList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNSRV002M0SelectHistList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNSRV002M0R1DTO> selectOptHistList(@RequestBody OPNSRV002M0P1DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV002M0R1DTO> result = OPNSRV002M0Service.selectOptHistList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/OPNSRV002M0SelectDetailList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNSRV002M0R2DTO> selectOptDetailList(@RequestBody OPNSRV002M0P1DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV002M0R2DTO> result = OPNSRV002M0Service.selectOptDetailList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 */
	// 4th grid data request from the ui side
	@RequestMapping(value="/OPNSRV002M0SelectDetailHistList", method=RequestMethod.POST, consumes="application/json")
	public List<OPNSRV002M0R3DTO> selectOptDetailHistList(@RequestBody OPNSRV002M0P2DTO inVo) {
		
		log.info(inVo.toString());
		
		List<OPNSRV002M0R3DTO> result = OPNSRV002M0Service.selectOptDetailHistList(inVo);
		
		log.info(result.toString());
		
		return result;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNSRV002M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> updateOptList(@RequestBody OPNSRV002M0R0DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		try {
			bResult = OPNSRV002M0Service.updateOptList(inVo);
			
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "OPNSRV002M0Controller Error : " + e.getMessage());
			//throw new Exception("OPNSRV002M0Controller Error : " + e.getMessage());
		}
		
		return resultMap;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNSRV002M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> insertOptList(@RequestBody OPNSRV002M0R0DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		try {
			bResult = OPNSRV002M0Service.insertOptList(inVo);
			
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "OPNSRV002M0Controller Error : " + e.getMessage());
			//throw new Exception("OPNSRV002M0Controller Error : " + e.getMessage());
		}
		
		return resultMap;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNSRV002M0UpdateDetailList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> updateOptDetailList(@RequestBody OPNSRV002M0R2DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		try {
			bResult = OPNSRV002M0Service.updateOptDetailList(inVo);
			
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "OPNSRV002M0Controller Error : " + e.getMessage());
			//throw new Exception("OPNSRV002M0Controller Error : " + e.getMessage());
		}
		
		return resultMap;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNSRV002M0InsertDetailList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> insertOptDetailList(@RequestBody OPNSRV002M0R2DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		try {
			bResult = OPNSRV002M0Service.insertOptDetailList(inVo);
			
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "OPNSRV002M0Controller Error : " + e.getMessage());
			//throw new Exception("OPNSRV002M0Controller Error : " + e.getMessage());
		}
		
		return resultMap;
		
	}
	
	/**
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/OPNSRV002M0DeleteDetailList", method=RequestMethod.POST, consumes="application/json")
	public HashMap<String, Object> deleteOptDetailList(@RequestBody OPNSRV002M0R2DTO inVo, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
		
		log.info(inVo.toString());

    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
    	inVo.setUsrId(sAdamsLoginDTO.getUsrId());
		try {
			bResult = OPNSRV002M0Service.deleteOptDetailList(inVo);
			
			if (bResult) {
				
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success !!!");
			} else {
				resultMap.put("resultCode"   , "300");
				resultMap.put("resultMessage", "Save Failed !!!");
			}
		} catch (Exception e) {
			resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "OPNSRV002M0Controller Error : " + e.getMessage());
			//throw new Exception("OPNSRV002M0Controller Error : " + e.getMessage());
		}
		
		return resultMap;
		
	}

	
}
