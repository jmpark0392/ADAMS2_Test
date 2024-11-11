package com.rds.adams.web.opn.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.opn.usr.dao.OPNUSR001M0DAO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P1DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0R0DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 11.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OPNUSR001M0Service {

	@Autowired
	private OPNUSR001M0DAO OPNUSR001M0DAO;

	/**
	 * 관리중인 메뉴 조회를 처리한다
	 * @param vo OPNUSR001M0P0DTO
	 * @return List<OPNUSR001M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNUSR001M0R0DTO> selectMenuList(OPNUSR001M0P0DTO inVo) {
		
		List<OPNUSR001M0R0DTO>	OPNUSR001M0P0DTOList = OPNUSR001M0DAO.selectMenuList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" OPNUSR001M0P0DTOList : " + OPNUSR001M0P0DTOList.toString());
		
		return OPNUSR001M0P0DTOList;
		
	}

	/**
	 * 메뉴 추가를 처리한다.
	 * @param vo OPNUSR001M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public void insertList(OPNUSR001M0P1DTO inVo) {
		OPNUSR001M0DAO.insertList(inVo);
		return;
	}

	/**
	 * 메뉴를 업데이트한다.
	 * @param vo OPNUSR001M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public void updateList(OPNUSR001M0P1DTO inVo) {
		OPNUSR001M0DAO.updateList(inVo);
	}
	
	/**
	 * 메뉴를 삭제한다.
	 * @param vo OPNUSR001M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public void deleteList(OPNUSR001M0P1DTO inVo) {
		OPNUSR001M0DAO.deleteList(inVo);
	}

}