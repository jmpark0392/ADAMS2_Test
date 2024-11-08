package com.rds.adams.web.mst.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.mst.sys.dao.MSTSYS002M0DAO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M0P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M0P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS002M0R0DTO;

@Service
public class MSTSYS002M0Service {
	
	@Autowired
	MSTSYS002M0DAO mSTSYS002M0DAO;
	
	public List<MSTSYS002M0R0DTO> selectList(MSTSYS002M0P0DTO inVo) {
		return mSTSYS002M0DAO.selectList(inVo);
	}
	
	public void insertList(MSTSYS002M0P1DTO inVo) {
		mSTSYS002M0DAO.insertList(inVo);
	}
	
	public void updateList(MSTSYS002M0P1DTO inVo) {
		mSTSYS002M0DAO.updateList(inVo);
	}

}