package com.rds.adams.web.mst.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0R0DTO;
import com.rds.adams.web.mst.sys.service.MSTSYS001M0Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class MSTSYS001M0Controller {
	
	@Autowired
	MSTSYS001M0Service mSTSYS001M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/MSTSYS001M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<MSTSYS001M0R0DTO> select(@RequestBody MSTSYS001M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<MSTSYS001M0R0DTO> result = mSTSYS001M0Service.selectList(inVo);
		
		for (MSTSYS001M0R0DTO mSTSYS001M0R0DTO : result) {
				log.info(mSTSYS001M0R0DTO.toString());
		}
		
		return result;
		
	}

	@RequestMapping(value="/MSTSYS001M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public void insert(@RequestBody MSTSYS001M0P1DTO inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS001M0Service.insertList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}
	
	@RequestMapping(value="/MSTSYS001M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public void update(@RequestBody MSTSYS001M0P1DTO inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS001M0Service.updateList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}

}
