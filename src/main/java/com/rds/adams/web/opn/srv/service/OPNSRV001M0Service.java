package com.rds.adams.web.opn.srv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.opn.srv.dao.OPNSRV001M0DAO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0R1DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 16.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-16 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OPNSRV001M0Service {

	@Autowired
	private OPNSRV001M0DAO oPNSRV001M0DAO;

	/**
	 * 서비스 정보 조회를 처리한다
	 * @param vo OPNSRV001M0P0DTO
	 * @return List<OPNSRV001M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNSRV001M0R0DTO> selectSrvcList(OPNSRV001M0P0DTO inVo) {
		
		List<OPNSRV001M0R0DTO>	oPNSRV001M0P0DTOList = oPNSRV001M0DAO.selectSrvcList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" OPNSRV001M0P0DTOList : " + oPNSRV001M0P0DTOList.toString());
		
		return oPNSRV001M0P0DTOList;
		
	}

	/**
	 * 서비스 이력 정보 조회를 처리한다
	 * @param vo OPNSRV001M0P1DTO
	 * @return List<OPNSRV001M0R1DTO>
	 * @exception Exception
	 */
	public List<OPNSRV001M0R1DTO> selectSrvcHistList(OPNSRV001M0P1DTO inVo) {
		
		List<OPNSRV001M0R1DTO>	oPNSRV001M0R1DTOList = oPNSRV001M0DAO.selectSrvcHistList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" OPNSRV001M0R1DTOList : " + oPNSRV001M0R1DTOList.toString());
		
		return oPNSRV001M0R1DTOList;
		
	}

	/**
	 * 서비스 정보 저장을 처리한다
	 * @param vo OPNSRV001M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean updateSrvcList(OPNSRV001M0R0DTO inVo) throws Exception {
		
		String sChkDate = "";
		String sChkCd   = "";
		
		try {
			
			sChkDate = oPNSRV001M0DAO.selectChkDate(inVo);
			
			if ( "1".equals(sChkDate) ) {
				// Error : "현재 날짜보다 과거를 시작할 수 없습니다."
				throw new Exception("OPNSRV001M0Service Error : You can't start the past more than the current date.");
			}
			
			sChkCd = oPNSRV001M0DAO.selectChkHist(inVo);
			
			oPNSRV001M0DAO.updateSrvc(inVo);
			
			if ( "1".equals(sChkCd) ) {
				oPNSRV001M0DAO.updateSrvcHistDay(inVo);	// Hist 시작일자 기준 Update
			} else {
				
				
				oPNSRV001M0DAO.updateSrvcHistBefor(inVo);
				
				oPNSRV001M0DAO.insertSrvcHist(inVo);
				
			}
		} catch (Exception e) {
			throw new Exception("OPNSRV001M0Service Error : " + e.getMessage());
		}
		log.debug(" OPNSRV001M0R0DTO : " + inVo.toString());
		
		return true;
		
	}

	/**
	 * 서비스 정보 저장을 처리한다
	 * @param vo OPNSRV001M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean insertSrvcList(OPNSRV001M0R0DTO inVo) throws Exception {
		
		String sChkDate = "";
		
		try {
			
			sChkDate = oPNSRV001M0DAO.selectChkDate(inVo);
			
			if ( "1".equals(sChkDate) ) {
				// Error : "현재 날짜보다 과거를 시작할 수 없습니다."
				throw new Exception("You can't start the past more than the current date.");
			}
			
			oPNSRV001M0DAO.insertSrvc(inVo);
			
			oPNSRV001M0DAO.insertSrvcHist(inVo);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		log.debug(" OPNSRV001M0R0DTO : " + inVo.toString());
		
		return true;
		
	}
	

}