package com.rds.adams.web.mst.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.mst.sys.dao.MSTSYS001M0DAO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P0DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0P1DTO;
import com.rds.adams.web.mst.sys.dto.MSTSYS001M0R0DTO;

@Service
public class MSTSYS001M0Service {
	
	@Autowired
	MSTSYS001M0DAO mSTSYS001M0DAO;
	
	public List<MSTSYS001M0R0DTO> selectList(MSTSYS001M0P0DTO inVo) {
		
		return mSTSYS001M0DAO.selectList(inVo);
		
	}

    public void insertList(MSTSYS001M0P1DTO inVo) {
        mSTSYS001M0DAO.insertList(inVo);
    }
    
    public void updateList(MSTSYS001M0P1DTO inVo) {
        mSTSYS001M0DAO.updateList(inVo);
    }

}