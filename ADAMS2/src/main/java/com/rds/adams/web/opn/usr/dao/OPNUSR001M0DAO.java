package com.rds.adams.web.opn.usr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0P1DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR001M0R0DTO;

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

@Mapper
public interface OPNUSR001M0DAO {

	/**
	 * 관리중인 메뉴를 조회한다
	 * @param vo OPNUSR001M0P0DTO
	 * @return List<OPNUSR001M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNUSR001M0R0DTO> selectMenuList(OPNUSR001M0P0DTO inVo);
	
	/**
	 * 메뉴를 추가한다
	 * @param vo OPNUSR001M0P1DTO
	 * @exception Exception
	 */
	public void insertList(OPNUSR001M0P1DTO inVo);
	
	/**
	 * 메뉴를 업데이트한다
	 * @param vo OPNUSR001M0P1DTO
	 * @exception Exception
	 */
	public void updateList(OPNUSR001M0P1DTO inVo);
	
	/**
	 * 메뉴를 삭제한다
	 * @param vo OPNUSR001M0P1DTO
	 * @exception Exception
	 */
	public void deleteList(OPNUSR001M0P1DTO inVo);

}