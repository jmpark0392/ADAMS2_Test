package com.rds.adams.web.opn.usr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.opn.usr.dto.OPNUSR002M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR002M0R0DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 26.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-26 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface OPNUSR002M0DAO {

	/**
	 * 고객사 목록 조회를 처리한다
	 * @param vo OPNUSR002M0P0DTO
	 * @return List<OPNUSR002M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNUSR002M0R0DTO> selectCsNoList(OPNUSR002M0P0DTO inVo);

	/**
	 * 고객사 정보를 저장한다
	 * @param vo OPNUSR002M0P1DTO
	 * @exception Exception
	 */
	public void updateCsNo(OPNUSR002M0R0DTO inVo);

	/**
	 * 고객사 사용 종료 시 사용자를 종료 저장한다
	 * @param vo OPNUSR002M0R0DTO
	 * @exception Exception
	 */
	public void updateUsrEnd(OPNUSR002M0R0DTO inVo);

	/**
	 * 고객사 관리자 사용자를 저장한다
	 * @param vo OPNUSR002M0R0DTO
	 * @exception Exception
	 */
	public void insertAdmUsr(OPNUSR002M0R0DTO inVo);

}