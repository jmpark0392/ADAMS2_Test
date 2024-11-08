package com.rds.adams.web.adm.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.adm.usr.dao.ADMUSR001M0DAO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P0DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P1DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0R0DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 10.
 * @author : PARK JUNMIN
 * E-MAIL  : jm.park@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADMUSR001M0Service {

	@Autowired
	private ADMUSR001M0DAO aDMUSR001M0DAO;

	/**
	 * 사용자 정보 조회를 처리한다
	 * @param vo ADMUSR001M0P0DTO
	 * @return List<ADMUSR001M0R0DTO>
	 * @exception Exception
	 */
	public List<ADMUSR001M0R0DTO> selectCompList(ADMUSR001M0P0DTO inVo) {
		
		List<ADMUSR001M0R0DTO>	ADMUSR001M0P0DTOList = aDMUSR001M0DAO.selectCompList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" ADMUSR001M0P0DTOList : " + ADMUSR001M0P0DTOList.toString());
		
		return ADMUSR001M0P0DTOList;
		
	}
	
	/*
	public void updateCompList(ADMUSR001M0P1DTO inVo) {
		aDMUSR001M0DAO.updateCompList(inVo);

	}
	*/
	
	
	public boolean updateCompList(ADMUSR001M0P1DTO inVo) {
        
		aDMUSR001M0DAO.updateCompList(inVo);
		return true; // 업데이트된 행의 수를 반환
    }
	
	/*
	
	public List<ADMUSR001M0R0DTO> updateCompList(ADMUSR001M0R0DTO inVo) {
		
		List<ADMUSR001M0R0DTO>	ADMUSR001M0P1DTOList = aDMUSR001M0DAO.updateCompList(inVo);	// 업데이트 대상 테이블 정보 DTO
		
		log.debug(" ADMUSR001M0P1DTOList : " + ADMUSR001M0P1DTOList.toString());
		
		return ADMUSR001M0P1DTOList;
		
	}
	*/
	
	public boolean updateServiceCode(ADMUSR001M0P1DTO inVo) {
        
		aDMUSR001M0DAO.updateServiceCode(inVo);
		return true; // 업데이트된 행의 수를 반환
    }
	
}