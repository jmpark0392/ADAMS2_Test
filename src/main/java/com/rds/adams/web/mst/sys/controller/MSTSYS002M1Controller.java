package com.rds.adams.web.mst.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.mst.sys.dto.MSTSYS002M1P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M1P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M1R0DTO;
import com.rds.adams.web.mst.sys.service.MSTSYS002M1Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class MSTSYS002M1Controller {
	
	@Autowired
	MSTSYS002M1Service mSTSYS002M1Service; 
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/MSTSYS002M1SelectList", method=RequestMethod.POST, consumes="application/json")
	public List<MSTSYS002M1R0DTO> select(@RequestBody MSTSYS002M1P0DTO inVo) {
		
		log.info(inVo.toString());
		
		List<MSTSYS002M1R0DTO> result = mSTSYS002M1Service.selectList(inVo);
		
		for (MSTSYS002M1R0DTO mSTSYS002M1R0DTO : result) {
				log.info(mSTSYS002M1R0DTO.toString());
		}
		
		return result;
		
	}

	@RequestMapping(value="/MSTSYS002M1InsertList", method=RequestMethod.POST, consumes="application/json")
	public void multipleInsert(@RequestBody List<MSTSYS002M1P1DTO> inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS002M1Service.multipleInsertList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}
	
	@RequestMapping(value="/MSTSYS002M1UpdateList", method=RequestMethod.POST, consumes="application/json")
	public void multipleUpdate(@RequestBody List<MSTSYS002M1P1DTO> inVo) {
		
		log.info(inVo.toString());
		try {
			mSTSYS002M1Service.multipleUpdateList(inVo);
			log.info("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("fail");
		}
		return;
	}

}
