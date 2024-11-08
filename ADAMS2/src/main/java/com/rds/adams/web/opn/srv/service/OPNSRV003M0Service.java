package com.rds.adams.web.opn.srv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.opn.srv.dao.OPNSRV003M0DAO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R1DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class OPNSRV003M0Service  {
    
    @Autowired
    private OPNSRV003M0DAO opnsrv003m0dao;

    public List<OPNSRV003M0R0DTO> getChartInfos(OPNSRV003M0P0DTO inVo) {
		
        List<OPNSRV003M0R0DTO> opnsrv003m0r0dtos = opnsrv003m0dao.getChartInfos(inVo);
		
        log.debug("opnsrv003m0r0dtos : " + opnsrv003m0r0dtos.toString());
		
        return opnsrv003m0r0dtos;
    }

    public List<OPNSRV003M0R1DTO> getGridData(OPNSRV003M0P1DTO inVo) {
		
        List<OPNSRV003M0R1DTO> opnsrv003m0r0dtos = opnsrv003m0dao.getGridData(inVo);
		
        log.debug("opnsrv003m0r0dtos : " + opnsrv003m0r0dtos.toString());
		
        return opnsrv003m0r0dtos;
    }
}
