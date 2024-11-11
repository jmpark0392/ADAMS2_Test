package com.rds.adams.web.adm.srv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P1DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P2DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R3DTO;

@Mapper
public interface ADMSRV001M0DAO {
	
	public List<ADMSRV001M0R0DTO> selectSubscriptionList(ADMSRV001M0P0DTO inVo);
      
	public List<ADMSRV001M0P2DTO> selectAllOptions();

	public List<ADMSRV001M0R0DTO> selectOptionInfoByCustomer(ADMSRV001M0P0DTO inVo);

	public List<ADMSRV001M0R0DTO> getUserSubscriptionInfo(ADMSRV001M0P0DTO inVo);

	public List<ADMSRV001M0R3DTO> selectSrvcUprcInfo(ADMSRV001M0P0DTO inVo);

	public String selectUsrCntChk(ADMSRV001M0P1DTO inVo);

	public void mergeServiceOption(ADMSRV001M0P1DTO inVo);

	public void mergeOptionDetails(ADMSRV001M0P1DTO inVo);

	public void updateCustomerServiceHistory(ADMSRV001M0P1DTO inVo);

	public void updateCustomerOptionHistory(ADMSRV001M0P1DTO inVo);

}
