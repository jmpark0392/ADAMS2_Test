package com.rds.adams.web.adm.srv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.adm.srv.dao.ADMSRV002M0DAO;
import com.rds.adams.web.adm.srv.dto.ADMSRV002M0P0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV002M0R0DTO;

import java.util.List;

@Service
public class ADMSRV002M0Service implements ADMSRV002M0DAO { 
    @Autowired
    private ADMSRV002M0DAO admsrv002m0dao;

//     public void fetchUserUsageDataAndCost(ADMSRV002M01P0DTO inVo ) {
//        admsrv002m0dao.fetchUserUsageDataAndCost();
//    }

     @Override
     public List<ADMSRV002M0R0DTO> fetchUserUsageDataAndCost (ADMSRV002M0P0DTO inputDto) {
        return admsrv002m0dao.fetchUserUsageDataAndCost(inputDto);
        
    }
}
