package com.rds.adams.web.mst.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.mst.sys.dto.MSTSYS002M0P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M0P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M0R0DTO;
import com.rds.adams.web.mst.sys.service.MSTSYS002M0Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class MSTSYS002M0Controller {
	
	@Autowired
	MSTSYS002M0Service mSTSYS002M0Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/MSTSYS002M0SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<MSTSYS002M0R0DTO> select(@RequestBody MSTSYS002M0P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<MSTSYS002M0R0DTO> result = mSTSYS002M0Service.selectList(inVo);
		
		for (MSTSYS002M0R0DTO mSTSYS002M0R0DTO : result) {
				log.info(mSTSYS002M0R0DTO.toString());
		}
		
		return result;
		
	}

	@RequestMapping(value="/MSTSYS002M0InsertList", method=RequestMethod.POST, consumes="application/json")
	public void insert(@RequestBody MSTSYS002M0P1DTO inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS002M0Service.insertList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}
	
	@RequestMapping(value="/MSTSYS002M0UpdateList", method=RequestMethod.POST, consumes="application/json")
	public void update(@RequestBody MSTSYS002M0P1DTO inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS002M0Service.updateList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}

}
