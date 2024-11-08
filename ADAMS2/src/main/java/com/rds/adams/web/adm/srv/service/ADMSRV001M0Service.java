package com.rds.adams.web.adm.srv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.adm.srv.dao.ADMSRV001M0DAO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P1DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P2DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R3DTO;

@Service
public class ADMSRV001M0Service {
    @Autowired
    private ADMSRV001M0DAO admsrv001m0dao;

    public List<ADMSRV001M0R0DTO> selectSubscriptionList(ADMSRV001M0P0DTO  inVo) {
        return admsrv001m0dao.selectSubscriptionList(inVo);
    }

    public List<ADMSRV001M0P2DTO> selectAllOptions() {
        return admsrv001m0dao.selectAllOptions();
    }

    public List<ADMSRV001M0R0DTO> selectOptionInfoByCustomer (ADMSRV001M0P0DTO inVo) {
        return admsrv001m0dao.selectOptionInfoByCustomer(inVo);
        
    }
    public List<ADMSRV001M0R0DTO> getUserSubscriptionInfo(ADMSRV001M0P0DTO inVo){
        return admsrv001m0dao.getUserSubscriptionInfo(inVo);
    }
    
    public List<ADMSRV001M0R3DTO> selectSrvcUprcInfo(ADMSRV001M0P0DTO inVo){
        return admsrv001m0dao.selectSrvcUprcInfo(inVo);
    }
    
    public String selectUsrCntChk(ADMSRV001M0P1DTO inVo){
        return admsrv001m0dao.selectUsrCntChk(inVo);
    }

    public void  mergeServiceOption(ADMSRV001M0P1DTO inVo) {
        admsrv001m0dao.mergeServiceOption(inVo);
        admsrv001m0dao.updateCustomerServiceHistory(inVo);
    }
    public void mergeOptionDetails(ADMSRV001M0P1DTO inVo) {
        admsrv001m0dao.mergeOptionDetails(inVo);
    }

    public void updateCustomerServiceHistory(ADMSRV001M0P1DTO inVo){
        admsrv001m0dao.updateCustomerServiceHistory(inVo);
    }

    public void updateCustomerOptionHistory(ADMSRV001M0P1DTO inVo){
        admsrv001m0dao.updateCustomerOptionHistory(inVo);
    }

}

