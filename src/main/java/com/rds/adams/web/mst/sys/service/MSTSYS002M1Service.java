package com.rds.adams.web.mst.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.mst.sys.dao.MSTSYS002M1DAO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M1P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M1P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M1R0DTO;

@Service
public class MSTSYS002M1Service {
	
	@Autowired
	MSTSYS002M1DAO mSTSYS002M0DAO;
	
	public List<MSTSYS002M1R0DTO> selectList(MSTSYS002M1P0DTO inVo) {
		return mSTSYS002M0DAO.selectList(inVo);
	}
	
	public void multipleInsertList(List<MSTSYS002M1P1DTO> inVo) {
		mSTSYS002M0DAO.multipleInsertList(inVo);
	}
	
	public void multipleUpdateList(List<MSTSYS002M1P1DTO> inVo) {
		mSTSYS002M0DAO.multipleUpdateList(inVo);
	}

}